package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.JwtTokenProvider;
import org.blog.api.domain.Account;
import org.blog.api.domain.Role;
import org.blog.api.domain.RoleName;
import org.blog.api.exception.AccountDuplicateException;
import org.blog.api.exception.AccountNotFoundException;
import org.blog.api.exception.RoleNotFoundException;
import org.blog.api.repository.AccountRepository;
import org.blog.api.repository.RoleRepository;
import org.blog.api.web.payload.AuthDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:21 PM
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final AccountRepository accounts;
    private final RoleRepository roles;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generationToken(authentication);

        Account account = accounts.findByEmail(request.getEmail())
                .orElseThrow(() -> new AccountNotFoundException(request.getEmail()));
        AuthDto.LoginResponse authDto = modelMapper.map(account, AuthDto.LoginResponse.class);
        authDto.setAccessToken(jwt);
        return authDto;
    }

    public Long join(AuthDto.JoinRequest request) {
        if (isExistEmail(request.getEmail())) {
            throw new AccountDuplicateException(request.getEmail());
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Account account = modelMapper.map(request, Account.class);
        Role role = this.roles.findByName(RoleName.USER).orElseThrow(RoleNotFoundException::new);
        account.getRoles().add(role);
        Account savedAccount = accounts.save(account);

        return savedAccount.getId();
    }

    public boolean isExistEmail(String email) {
        return accounts.findByEmail(email).isPresent();
    }


}
