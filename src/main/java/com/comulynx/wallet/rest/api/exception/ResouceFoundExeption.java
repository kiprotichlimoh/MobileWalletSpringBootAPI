package com.comulynx.wallet.rest.api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ResouceFoundExeption extends Exception {

    private static final long serialVersionUID = 1L;

    public ResouceFoundExeption(String message) {
        super(message);
    }
}