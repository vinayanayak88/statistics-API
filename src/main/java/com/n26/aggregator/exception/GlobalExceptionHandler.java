package com.n26.aggregator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.n26.aggregator.common.ErrorResponse;
import com.n26.aggregator.constant.Constant;

/**
 * Global exception handler class which returns meaningful
 * error responses in case if exception is thrown in internal
 * methods
 *
 * @author vinayanayak
 * @date 06-Jan-2018
 * GlobalExceptionHandler.java
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(ValidationException exception){
        logger.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getErrorMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse(Constant.UNEXPECTED_ERROR);
    }
    
    @ResponseBody
    @ExceptionHandler(value = StatisticsNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleException(StatisticsNotFoundException exception){
        return new ErrorResponse(exception.getErrorMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(value = InvalidTimestampException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleException(InvalidTimestampException exception){
        return new ErrorResponse(exception.getErrorMessage());
    }
}
