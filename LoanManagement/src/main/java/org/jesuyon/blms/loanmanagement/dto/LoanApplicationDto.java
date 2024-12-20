package org.jesuyon.blms.loanmanagement.dto;

import lombok.*;
import org.jesuyon.blms.loanmanagement.domain.ApplicationStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationDto {
    private String id;
    private UserDto user;
    private Double requestedAmount;
    private ApplicationStatus status;
}
