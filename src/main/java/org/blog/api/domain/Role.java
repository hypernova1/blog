package org.blog.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.blog.api.domain.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 8:09 PM
 */

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role extends DateAudit {

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

}
