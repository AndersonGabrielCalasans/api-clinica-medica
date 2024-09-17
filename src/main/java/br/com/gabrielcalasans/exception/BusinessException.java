package br.com.gabrielcalasans.exception;

import java.io.Serial;
import java.io.Serializable;

public class BusinessException extends RuntimeException
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public static class JaCadastradoException extends BusinessException {
        public JaCadastradoException(String message) {
            super(message);
        }
    }

    public static class IdInvalidoException extends BusinessException {
        public IdInvalidoException(String message) {
            super(message);
        }
    }

    public static class NotFoundException extends BusinessException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
