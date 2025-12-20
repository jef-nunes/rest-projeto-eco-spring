package com.example.eco.api.controllers.util;

import com.example.eco.domain.models.ResponseModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    private static class ResponseModelStatus {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
    }

    public static ResponseModel buildSuccessResponse(List<?> data) {
        return new ResponseModel(ResponseModelStatus.SUCCESS,data, LocalDateTime.now(), new ArrayList<>());
    }

    public static ResponseModel buildFailureResponse(List<String> errors) {
        return new ResponseModel(ResponseModelStatus.FAILURE,new ArrayList<>(), LocalDateTime.now(), errors);
    }
}
