package com.example.projectdemex.model;

import lombok.Getter;

@Getter
public enum Role {
    USER("Пользователь"),
    ADMIN("Администратор"),
    MODERATOR("Модератор");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}