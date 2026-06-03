package com.project.booklandserver.security;

import com.project.booklandserver.model.UserRole;

public record AuthResponse(String token, UserRole role) {
}
