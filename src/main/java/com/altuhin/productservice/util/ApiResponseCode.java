package com.altuhin.productservice.util;

public enum ApiResponseCode {
    SAVE_SUCCESS("SAVE_SUCCESS"),
    UPDATE_SUCCESS("UPDATE_SUCCESS"),
    DELETE_SUCCESS("DELETE_SUCCESS"),
    FETCH_DATA_SUCCESS("FETCH_DATA_SUCCESS"),
    WRONG_CREDENTIALS("WRONG_CREDENTIALS"),
    BAD_REQUEST("BAD_REQUEST"),
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    CONSTRAINT_VIOLATION_ERROR("CONSTRAINT_VIOLATION_ERROR"),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE"),
    DUPLICATE_ENTITY("DUPLICATE_ENTITY"),
    ERROR("ERROR"),
    NOT_FOUND("NOT_FOUND"),
    NONE("NONE"),
    USER_INFORM_ERROR("USER_INFORM_ERROR");
    
    private final String message;
    
     ApiResponseCode(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return this.message;
    }
}
