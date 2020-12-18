package ru.eugene.backend;

public class NotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Объект не найден";
    }
}
