package com.mercadolibre.ismutant.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends DefaultHandlerExceptionResolver {

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({AnalyzeMutantException.class})
    public @ResponseBody
    ResponseEntity<Void> handleAnalyzeMutantException(AnalyzeMutantException ex) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}