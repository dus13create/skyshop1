package org.skypro.skyshop1.model;

public final class ShopError {
    private final String code;    // Код ошибки
    private final String message; // Сообщение об ошибке

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() { return code; }
    public String getMessage() { return message; }
}
