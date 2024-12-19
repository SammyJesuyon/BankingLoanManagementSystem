package org.jesuyon.blms.usermanagement.user.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clerk extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
