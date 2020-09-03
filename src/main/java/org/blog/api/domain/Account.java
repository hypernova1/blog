package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blog.api.domain.audit.DateAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 9:50 PM
 */
@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends DateAudit {

    @Column(length = 30, unique = true, nullable = false, updatable = false)
    private String email;

    @Column(length = 30, nullable = false, updatable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Builder
    private Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
