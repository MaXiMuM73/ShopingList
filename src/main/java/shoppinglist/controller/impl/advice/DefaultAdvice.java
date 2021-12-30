package shoppinglist.controller.impl.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shoppinglist.exception.AppException;

@RestControllerAdvice
public class DefaultAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AppException.class})
    public String handleAppException(RuntimeException e) {
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public String handleUnknownException() {
        return "Unknown error.";
    }
}