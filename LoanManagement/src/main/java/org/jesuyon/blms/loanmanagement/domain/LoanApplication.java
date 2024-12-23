package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.*;
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
    @ManyToOne
    private User user;
    private Double requestedAmount;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status=ApplicationStatus.PENDING;

    @Version
    private int version;
}
