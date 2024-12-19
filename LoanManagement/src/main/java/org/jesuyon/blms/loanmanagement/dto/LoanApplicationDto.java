package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.jesuyon.blms.loanmanagement.domain.ApplicationStatus;

@Getter
@Setter
public class LoanApplicationDto {
    private String id;
    private String userId;
    private Double requestedAmount;
    private ApplicationStatus status;
}
