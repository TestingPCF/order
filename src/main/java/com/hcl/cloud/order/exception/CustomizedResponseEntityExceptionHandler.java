package com.hcl.cloud.order.exception;

import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.Response;
import com.hcl.cloud.order.entity.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Status statusObject = new Status(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError().getDefaultMessage());
        Response response = new Response.Builder(statusObject).build();
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
