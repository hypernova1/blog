package org.blog.api.repository;

import org.blog.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:32 PM
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
