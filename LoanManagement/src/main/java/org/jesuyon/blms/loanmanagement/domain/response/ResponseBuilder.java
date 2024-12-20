package org.jesuyon.blms.loanmanagement.domain.response;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<BaseResponse<T>> buildResponse(String message, T data, int responseCode) {
        BaseResponse<T> response = new BaseResponse<>(message, data, responseCode);
        return ResponseEntity.ok(response);
    }
}
