package br.com.gabrielcalasans.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class PacienteNotFoundException extends RuntimeException {
    
    public PacienteNotFoundException(String message) {
        super(message);
    }
    
    public PacienteNotFoundException() {
    }
    
}
