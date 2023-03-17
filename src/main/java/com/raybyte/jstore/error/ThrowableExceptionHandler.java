package com.raybyte.jstore.error;

import com.raybyte.jstore.controller.LogCreateController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(101)
@RestControllerAdvice
public class ThrowableExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogCreateController.class);

  @ResponseBody
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handleThrowable(final Throwable t) {
    LOGGER.error("UnExpectedException", t);
    return new ResponseEntity<>(new Object(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
