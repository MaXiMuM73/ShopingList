package shoppinglist.controller.impl.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleCategoryException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}