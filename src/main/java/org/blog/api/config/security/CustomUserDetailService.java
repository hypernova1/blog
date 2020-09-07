package org.blog.api.config.security;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Account;
import org.blog.api.exception.AccountNotFoundException;
import org.blog.api.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 9:16 PM
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accounts;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accounts.findByEmail(email).orElseThrow(() -> new AccountNotFoundException(email));
        return UserPrincipal.create(account);
    }

}
