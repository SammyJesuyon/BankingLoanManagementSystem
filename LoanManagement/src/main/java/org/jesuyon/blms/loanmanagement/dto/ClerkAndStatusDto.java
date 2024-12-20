package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.jesuyon.blms.loanmanagement.domain.LoanStatus;

@Getter
@Setter
public class ClerkAndStatusDto {
    String clerkId;
    LoanStatus status;
}
