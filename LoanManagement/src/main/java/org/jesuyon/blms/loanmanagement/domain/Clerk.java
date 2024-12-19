package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clerk {

    @Id
    private String id;
    private String email;
    private String password;

    private String role = "CLERK";
}
