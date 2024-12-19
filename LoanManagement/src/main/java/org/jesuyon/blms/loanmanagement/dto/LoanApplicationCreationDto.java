package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplicationCreationDto {
    private String userId;
    private Double requestedAmount;
}
