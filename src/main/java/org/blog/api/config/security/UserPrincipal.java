package org.blog.api.config.security;

import lombok.*;
import org.blog.api.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 8:13 PM
 */

@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;

    public static UserPrincipal create(Account account) {
        Set<GrantedAuthority> authorities = account.getRoles()
                .stream()
                .map(role ->new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toSet());

        return new UserPrincipal(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                authorities,
                account.isActive()
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
