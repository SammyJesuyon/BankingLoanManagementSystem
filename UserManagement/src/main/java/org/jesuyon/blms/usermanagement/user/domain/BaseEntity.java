package org.jesuyon.blms.usermanagement.user.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id@GeneratedValue
    private String id;

    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
