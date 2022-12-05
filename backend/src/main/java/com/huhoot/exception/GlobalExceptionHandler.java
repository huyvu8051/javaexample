package com.huhoot.exception;

import com.huhoot.config.mvc.CustomBodyResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({LockedException.class, BadCredentialsException.class, CustomException.class})
    public CustomBodyResponseDTO handleAuthenticationException(Exception e) {
        log.debug("handleAuthenticationException" + e.getMessage());
        return CustomBodyResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({NoSuchElementException.class})
    public CustomBodyResponseDTO nullPointerException(Exception e) {
        return CustomBodyResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({AccountNotFoundException.class, NotYourOwnException.class, ChallengeException.class, StudentAddException.class, UsernameExistedException.class, AccountException.class})
    public CustomBodyResponseDTO handleServiceException(Exception e) {
        return CustomBodyResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomBodyResponseDTO handleMethodArgumentNotValidException(Exception e) {
        log.error(e.getMessage());
        return CustomBodyResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public CustomBodyResponseDTO handleUnwantedException(Exception e) {
        log.error(e.getMessage());
        return CustomBodyResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }
}