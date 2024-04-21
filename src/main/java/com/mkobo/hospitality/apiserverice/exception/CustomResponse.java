package com.mkobo.hospitality.apiserverice.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomResponse<T> {

    private T data;
    private List<ErrorDetail> errors;

    @Getter
    @Setter
    public static class ErrorDetail {
        private String field;
        private String message;

        public ErrorDetail(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}

