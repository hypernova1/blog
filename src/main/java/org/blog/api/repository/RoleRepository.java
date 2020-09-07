package org.blog.api.repository;

import org.blog.api.domain.Role;
import org.blog.api.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 8:27 PM
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
