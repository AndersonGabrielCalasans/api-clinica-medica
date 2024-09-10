package br.com.gabrielcalasans.exception;

public class MedicoNotFoundException extends RuntimeException {
    
    public MedicoNotFoundException(String message) {
        super(message);
    }
    
    public MedicoNotFoundException() {
    }
    
}
