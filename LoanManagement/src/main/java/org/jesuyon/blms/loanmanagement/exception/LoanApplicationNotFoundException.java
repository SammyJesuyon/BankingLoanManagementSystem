package org.jesuyon.blms.loanmanagement.exception;

public class LoanApplicationNotFoundException extends RuntimeException{
    public LoanApplicationNotFoundException(String message) {
        super(message);
    }
    public LoanApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
