package org.jesuyon.blms.loanmanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoanException extends RuntimeException {
    private final HttpStatus status;

    public LoanException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
    public LoanException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
