package org.jesuyon.blms.usermanagement.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "UserTbl")
public class User extends BaseEntity{
    private String firstName;
    private String lastName;

    @Column(name = "dateOfBirth")
    private Date dob;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isActive;
}
