package com.example.eco.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {
    private String status;
    private List<?> data;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ResponseModel(String status, List<?> data, LocalDateTime timestamp, List<String> errors) {
        this.status = status;
        this.data = data;
        this.timestamp = timestamp;
        this.errors = errors;
    }
}
