package com.huhoot.config.mvc;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Custom body response wrapper class.
 *
 * @author HuyVu
 * @CreatedDate 28/9/2022
 */
@Builder
public class CustomBodyResponseDTO implements Serializable {
    private HttpStatus status;
    private String message;

    /**
     * Original body response.
     */
    @Getter
    private Object data;

    public int getStatus() {
        return status.value();
    }

    public String getMessage() {
        if (message == null) {
            message = status.getReasonPhrase();
        }
        return message;
    }
}