package com.example.metricconverter.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.metricconverter.controller.MetricConverterTddController;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(FormulaNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(FormulaNotFoundException exception ,HttpServletRequest request) {
        
	
        ResponseEntity<Object> entity = new ResponseEntity<>("formula shd not be null or empty",HttpStatus.NOT_FOUND);
        return entity;
    }

	
	

	@ExceptionHandler(BothInputRequestParamEqualException.class)
    public ResponseEntity<Object> handleExceptions(BothInputRequestParamEqualException exception ,HttpServletRequest request) {
        
	
        ResponseEntity<Object> entity = new ResponseEntity<>("Both input is same which is not allowed",HttpStatus.BAD_REQUEST);
        return entity;
    }

	
	
	
	@ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<Object> handleExceptions(InputMismatchException exception ,HttpServletRequest request) {
        
	
        ResponseEntity<Object> entity = new ResponseEntity<>("both input can not be null or empty",HttpStatus.BAD_REQUEST);
        return entity;
    }
	
	
	
	@ExceptionHandler(ConnectionRefusedException.class)
    public ResponseEntity<Object> handleExceptions(ConnectionRefusedException exception ,HttpServletRequest request) {
        
	
        ResponseEntity<Object> entity = new ResponseEntity<>("caught exception at the time of calling crud service",HttpStatus.BAD_REQUEST);
        return entity;
    }
	
}
