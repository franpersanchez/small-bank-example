package org.example.smallbankexample.infraestructure.rest.advice;

import jdk.jshell.spi.ExecutionControl;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.example.smallbankexample.infraestructure.addapters.exceptions.WalletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleEmptyInput(UserException e) {
        return new ResponseEntity<String>(e.getErrorMessage(), e.getErrorCode());
    }

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<String> handleEmptyInput(WalletException e) {
        return new ResponseEntity<String>(e.getErrorMessage(), e.getErrorCode());
    }
}
