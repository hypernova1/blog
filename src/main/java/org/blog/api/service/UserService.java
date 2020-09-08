package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.UserPrincipal;
import org.blog.api.domain.Account;
import org.blog.api.exception.AccountNotFoundException;
import org.blog.api.repository.AccountRepository;
import org.blog.api.web.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by melchor
 * Date: 2020/09/05
 * Time: 12:19 AM
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final AccountRepository accounts;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto.InfoResponse modifyInfo(UserDto.ModifyRequest request, UserPrincipal authUser) {
        Account account = accounts.findByEmail(authUser.getEmail()).orElseThrow(() -> new AccountNotFoundException(authUser.getEmail()));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        account.update(request);
        return modelMapper.map(account, UserDto.InfoResponse.class);
    }
}
