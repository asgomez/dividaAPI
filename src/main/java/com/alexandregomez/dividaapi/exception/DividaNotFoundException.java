package com.alexandregomez.dividaapi.exception;

public class DividaNotFoundException extends RuntimeException {

    public DividaNotFoundException(Long id) {
        super("Dívida não encontrada para o ID: " + id);
    }
}
