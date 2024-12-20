package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ApproveLoanDto {
    String applicationId;
    String clerkId;
    BigDecimal interestRate;
}