package com.dut.education.controllers;

import com.dut.education.entitys.exception.ExceptionInfo;
import com.dut.education.entitys.exception.NoSuchCityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> notFoundException(NoSuchCityException e){
        ExceptionInfo data = new ExceptionInfo();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
   /* @ExceptionHandler
    public ResponseEntity<ExceptionInfo> missingAccess(NoSuchCityException e){
        ExceptionInfo data = new ExceptionInfo();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }*/
    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> unknownException(Exception e){
        ExceptionInfo data = new ExceptionInfo();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
