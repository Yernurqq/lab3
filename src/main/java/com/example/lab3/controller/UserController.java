package com.example.lab3.controller;

import com.example.lab3.exception.CustomException;
import com.example.lab3.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    // Временное хранилище пользователей
    private Map<Long, User> userMap = new HashMap<>();

    // Эндпоинт для создания пользователя с валидацией
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        Long id = Long.valueOf(userMap.size() + 1);
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    // Эндпоинт для обновления информации о пользователе
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        if (!userMap.containsKey(id)) {
            throw new CustomException("Пользователь с ID " + id + " не найден.");
        }
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    // Эндпоинт для удаления пользователя
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
            return "Пользователь с ID " + id + " удален.";
        } else {
            return "Пользователь с ID " + id + " не найден.";
        }
    }

    // Эндпоинт, который выбрасывает исключение
    @GetMapping("/exception")
    public void throwException() {
        throw new CustomException("Это кастомное исключение");
    }

    // Обработка ошибок валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }

    // Обработка кастомного исключения
    @ExceptionHandler(CustomException.class)
    public Map<String, String> handleCustomException(CustomException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}