package br.com.gabrielcalasans.exception;

public class MedicoIdInvalidoException extends RuntimeException {
    
    public MedicoIdInvalidoException(String message) {
        super(message);
    }
    
    public MedicoIdInvalidoException() {
    }
}
