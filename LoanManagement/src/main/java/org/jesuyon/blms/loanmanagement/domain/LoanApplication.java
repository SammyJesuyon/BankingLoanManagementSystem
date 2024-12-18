package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication extends BaseEntity{
    private String userId;
    private Double requestedAmount;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status=ApplicationStatus.PENDING;
}
