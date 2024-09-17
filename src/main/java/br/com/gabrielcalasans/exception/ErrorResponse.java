package br.com.gabrielcalasans.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String errorName;
    private String errorCode;
    private Integer statusCode;
    private String message;

}
