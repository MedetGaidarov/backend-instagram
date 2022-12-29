package com.example.serverinstagram.ui.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponseDto {
    private String status = "failed";

    private String message = "Something went wrong. Try again or contact to developers";

    private Object payload = null;

    @Builder
    public DefaultResponseDto(String status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public DefaultResponseDto(String status, String message) {
        this(status, message, null);
    }

}
