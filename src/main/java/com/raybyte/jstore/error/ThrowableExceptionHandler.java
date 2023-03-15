package com.raybyte.jstore.error;

import org.apache.log4j.Logger;
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

  Logger logger = Logger.getLogger(ThrowableExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handleThrowable(final Throwable t) {
    logger.error("UnhandledException", t);
    return new ResponseEntity<>(new Object(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
