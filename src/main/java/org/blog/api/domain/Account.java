package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blog.api.domain.audit.DateAudit;
import org.blog.api.web.payload.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @ManyToMany
    private Collection<Role> roles = new HashSet<>();

    @Builder
    private Account(String email, String username, String password, Collection<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void update(UserDto.ModifyRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();

    }
}
