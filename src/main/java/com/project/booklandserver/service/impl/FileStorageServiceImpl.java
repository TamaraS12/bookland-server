package com.project.booklandserver.service.impl;

import com.project.booklandserver.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path uploadPath =
            Paths.get("uploads/books");

    public String save(MultipartFile file) {

        try {

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            if (file.getOriginalFilename() == null) {
                throw new RuntimeException("File name is empty");
            }

            Files.copy(
                    file.getInputStream(),
                    uploadPath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return file.getOriginalFilename();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    public void delete(String fileName) {

        try {

            if (fileName != null) {
                Files.deleteIfExists(
                        uploadPath.resolve(fileName)
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
