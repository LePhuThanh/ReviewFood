package com.project.reviewfood.handlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{
    private String status;
    public CustomException(String status, String message) {
        super(message);
        this.status = status;
    }
}
