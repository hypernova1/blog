package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blog.api.domain.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by melchor
 * Date: 2020/10/03
 * Time: 4:01 PM
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends DateAudit {

    private String content;
    @ManyToOne
    private Account writer;

}
