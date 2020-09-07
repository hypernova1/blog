package org.blog.api.repository;

import org.blog.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 8:27 PM
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
