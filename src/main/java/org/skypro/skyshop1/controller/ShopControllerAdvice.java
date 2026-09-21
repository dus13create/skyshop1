package org.skypro.skyshop1.controller;
import org.skypro.skyshop1.exception.NoSuchProductException;
import org.skypro.skyshop1.model.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    // Обрабатываем исключение NoSuchProductException и возвращаем JSON с ошибкой и 404
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity handleNoSuchProductException(NoSuchProductException e) {
        ShopError error = new ShopError("PRODUCT_NOT_FOUND", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
