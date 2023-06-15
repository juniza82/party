package com.juniza82.party.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {

    public static final String DELETED_IS_FALSE = "deleted = false";

    @CreatedBy
    @Column(updatable = false)
    @Comment("생성자")
    private String createdBy;

    @LastModifiedBy
    @Column(updatable = false)
    @Comment("수정자")
    private String modifiedBy;

    @Comment("삭제 여부")
    private boolean deleted;

    public boolean isUndeleted() {
        return !isDeleted() ;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = true;
        super.delete();
    }
}
