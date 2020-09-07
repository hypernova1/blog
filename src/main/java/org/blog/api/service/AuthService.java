package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.UserPrincipal;
import org.blog.api.domain.Account;
import org.blog.api.exception.AccountDuplicateException;
import org.blog.api.exception.AccountNotFoundException;
import org.blog.api.repository.AccountRepository;
import org.blog.api.web.payload.AuthDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:21 PM
 */

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final AccountRepository accounts;

    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {

        Account account = accounts.findByEmail(request.getEmail())
                .orElseThrow(() -> new AccountNotFoundException(request.getEmail()));

        if (!account.getPassword().equals(request.getPassword())) {
            throw new AccountNotFoundException(request.getEmail());
        }

        return modelMapper.map(account, AuthDto.LoginResponse.class);
    }

    public Long join(AuthDto.JoinRequest request) {
        if (isExistEmail(request.getEmail())) {
            throw new AccountDuplicateException(request.getEmail());
        }

        Account account = modelMapper.map(request, Account.class);
        Account savedAccount = accounts.save(account);

        return savedAccount.getId();
    }

    public boolean isExistEmail(String email) {
        return accounts.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accounts.findByEmail(email).orElseThrow(() -> new AccountNotFoundException(email));
        return UserPrincipal.create(account);
    }
}
