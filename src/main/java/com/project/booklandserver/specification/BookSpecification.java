package com.project.booklandserver.specification;

import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.model.Author;
import com.project.booklandserver.model.Book;
import com.project.booklandserver.model.Genre;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookSpecification {

    public static Specification<Book> search(BookSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(request.authorId())
                    .ifPresent(authorId -> {
                        Join<Book, Author> authorJoin = root.join("author", JoinType.INNER);
                        Predicate id = cb.equal(authorJoin.get("id"), authorId);
                        predicates.add(id);
                    });

            Optional.ofNullable(request.genreId())
                    .ifPresent(genreId -> {
                        Join<Book, Genre> genreJoin = root.join("genres", JoinType.INNER);
                        query.groupBy(root.get("id"));
                        Predicate id = cb.equal(genreJoin.get("id"), genreId);
                        predicates.add(id);
                    });

            Optional.ofNullable(request.title())
                    .filter(title -> !title.isBlank())
                    .ifPresent(title -> {
                        Predicate titlePredicate = cb.like(
                                cb.lower(root.get("title")),
                                "%" + title.toLowerCase() + "%"
                        );
                        predicates.add(titlePredicate);
                    });

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
