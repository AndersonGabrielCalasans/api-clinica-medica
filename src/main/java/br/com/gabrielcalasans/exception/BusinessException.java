package br.com.gabrielcalasans.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String ERROR_NAME;
    private final String CODE_ERROR;
    private final Integer STATUS_CODE;

    public BusinessException(String ERROR_NAME, String CODE_ERROR, Integer STATUS_CODE, String message) {
        super(message);
        this.ERROR_NAME = ERROR_NAME;
        this.CODE_ERROR = CODE_ERROR;
        this.STATUS_CODE = STATUS_CODE;
    }

    public static class JaCadastradoException extends BusinessException {
        private static final String ERROR_NAME = "JaCadastradoException";
        private static final String CODE_ERROR = "BE-001";
        private static final Integer STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();
        private static final String MESSAGE = "";

        public JaCadastradoException(String message) {
            super(ERROR_NAME, CODE_ERROR, STATUS_CODE, message);
        }
    }

    public static class IdInvalidoException extends BusinessException {
        private static final String ERROR_NAME = "IdInvalidoException";
        private static final String CODE_ERROR = "BE-002";
        private static final Integer STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();
        private static final String MESSAGE = "";

        public IdInvalidoException(String message) {
            super(ERROR_NAME, CODE_ERROR, STATUS_CODE, message);
        }
    }

    public static class NotFoundException extends BusinessException {
        private static final String ERROR_NAME = "NotFoundException";
        private static final String CODE_ERROR = "BE-003";
        private static final Integer STATUS_CODE = Response.Status.NOT_FOUND.getStatusCode();
        private static final String MESSAGE = "";

        public NotFoundException(String message) {
            super(ERROR_NAME, CODE_ERROR, STATUS_CODE, message);
        }
    }
}
