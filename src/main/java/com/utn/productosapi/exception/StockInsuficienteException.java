package com.utn.productosapi.exception;
public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
    public StockInsuficienteException(Long id, Integer stockActual, Integer stockRequerido) {
        super(String.format("Stock insuficiente para el producto ID %d. Stock actual: %d, requerido: %d", 
                id, stockActual, stockRequerido));
    }
}
