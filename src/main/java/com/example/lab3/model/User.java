package com.example.lab3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class User {
    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @Min(value = 19, message = "Возраст должен быть больше 18 лет")
    private int age;

    public User() {
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
