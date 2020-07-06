package org.sam.blog.doamin;

import lombok.Getter;
import org.sam.blog.doamin.audit.DateAudit;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class Member extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

}
