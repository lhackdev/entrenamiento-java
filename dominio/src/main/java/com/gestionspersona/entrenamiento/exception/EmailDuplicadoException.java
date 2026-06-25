package com.gestionspersona.entrenamiento.exception;

public class EmailDuplicadoException extends RuntimeException{
    public EmailDuplicadoException(String email) {
        super("El correo ya está en uso: " + email);
    }
}
