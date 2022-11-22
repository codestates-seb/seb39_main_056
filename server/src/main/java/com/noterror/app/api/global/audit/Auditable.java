package com.noterror.app.api.global.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * JPA Auditing
 * Entity 생성 시간, 수정 시간 적용
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @CreatedDate
    @Column(updatable = false, name = "create_date")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "edit_date")
    private LocalDateTime editDate;
}
