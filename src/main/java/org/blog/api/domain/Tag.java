package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blog.api.domain.audit.DateAudit;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by melchor
 * Date: 2020/09/19
 * Time: 12:06 AM
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends DateAudit {
    @Column(unique = true, nullable = false)
    private String name;
}
