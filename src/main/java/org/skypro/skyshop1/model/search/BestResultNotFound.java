package org.skypro.skyshop1.model.search;

// Собственное исключение для "лучший результат не найден"
public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не найден подходящий результат для запроса: '" + query + "'");
    }
}