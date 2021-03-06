package org.blog.api.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 9:31 PM
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
@EqualsAndHashCode(of = "id", callSuper = false)
public class DateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedDate;

}
