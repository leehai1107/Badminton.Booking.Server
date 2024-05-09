package com.main.badminton.booking.exception;

import com.main.badminton.booking.utils.wapper.API;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.hibernate.TransactionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.sqm.sql.ConversionException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Global exception
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public API.Response<Object> handleGlobalException(Exception ex, WebRequest request) {
//        return API.Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", "Internal server error");
//    }

    // Bad request exception
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseBody
    public API.Response<Object> handleBadRequestException(Exception ex, WebRequest request) {
        return API.Response.error(HttpStatus.BAD_REQUEST, ex.getMessage(), "Bad request");
    }



    // Unauthorized exception
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public API.Response<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return API.Response.error(HttpStatus.UNAUTHORIZED, ex.getMessage(), "Unauthorized access");
    }

    // Access denied exception
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public API.Response<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return API.Response.error(HttpStatus.FORBIDDEN, ex.getMessage(), "Access denied");
    }

    // Method argument not valid exception (e.g., validation fails in @Validated)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public API.Response<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return API.Response.error(HttpStatus.BAD_REQUEST, "Validation error", String.join(", ", errorMessages));
    }

    // Constraint violation exception (e.g., database constraints)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public API.Response<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errorMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return API.Response.error(HttpStatus.BAD_REQUEST, "Constraint violation", String.join(", ", errorMessages));
    }

    // Conversion exception (e.g., conversion fails in Spring Data)
    @ExceptionHandler(ConversionException.class)
    @ResponseBody
    public API.Response<Object> handleConversionException(ConversionException ex, WebRequest request) {
        return API.Response.error(HttpStatus.BAD_REQUEST, ex.getMessage(), "Conversion error");
    }

    // Data access exception (e.g., database errors)
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public API.Response<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        return API.Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "Data access error", ex.getMessage());
    }

    // Unsupported media type exception (e.g., wrong content type)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public API.Response<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, WebRequest request) {
        return API.Response.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type", ex.getMessage());
    }

    // SQL exception
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public API.Response<Object> handleSQLException(SQLException ex, WebRequest request) {
        return API.Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "SQL error occurred", ex.getMessage());
    }

    // JPA exception
    @ExceptionHandler(PersistenceException.class)
    @ResponseBody
    public API.Response<Object> handleJpaException(PersistenceException ex, WebRequest request) {
        return API.Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "JPA error occurred", ex.getMessage());
    }


    // JPA entity not found exception
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public API.Response<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return API.Response.error(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
    }

    // JPA optimistic locking failure exception
    @ExceptionHandler(OptimisticLockException.class)
    @ResponseBody
    public API.Response<Object> handleOptimisticLockException(OptimisticLockException ex, WebRequest request) {
        return API.Response.error(HttpStatus.CONFLICT, "Optimistic locking failure", ex.getMessage());
    }


    // SQL grammar exception (e.g., syntax error in SQL query)
    @ExceptionHandler(SQLGrammarException.class)
    @ResponseBody
    public API.Response<Object> handleSQLGrammarException(SQLGrammarException ex, WebRequest request) {
        return API.Response.error(HttpStatus.BAD_REQUEST, "SQL grammar error", ex.getMessage());
    }


    // JPA transaction exception
    @ExceptionHandler(TransactionException.class)
    @ResponseBody
    public API.Response<Object> handleTransactionException(TransactionException ex, WebRequest request) {
        return API.Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "JPA transaction error", ex.getMessage());
    }

}