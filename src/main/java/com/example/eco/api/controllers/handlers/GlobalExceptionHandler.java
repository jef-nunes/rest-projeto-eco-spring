package com.example.eco.api.controllers.handlers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.domain.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

// Gerencia exceções retornando uma response adequada ao client
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseModel> handleNotFound(ResourceNotFoundException ex) {
        String errorMsg = "Recurso não encontrado.";
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBuilder.buildFailureResponse(List.of(errorMsg)));
    }

    // Erros de validação (Jakarta Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Lista de mensagens de erro
        List<String> errors = new ArrayList<>();
        // As mensagens de erro são providenciadas pelo Jakarta Validation
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.buildFailureResponse(errors));
    }

    // MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseModel> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMsg = "URL da requisição é inválida.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.buildFailureResponse(List.of(errorMsg)));
    }

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseModel> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMsg = "URL da requisição é inválida.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.buildFailureResponse(List.of(errorMsg)));
    }
    
    // Qualquer outra exceção
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> handleGenericException(Exception ex) {
        String errorMsg = "Erro interno do servidor.";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.buildFailureResponse(List.of(errorMsg)));
    }
}