package org.blog.api.repository;

import org.blog.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:32 PM
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}
