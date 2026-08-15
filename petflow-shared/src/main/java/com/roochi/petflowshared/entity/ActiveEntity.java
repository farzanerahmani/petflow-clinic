package com.roochi.petflowshared.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class ActiveEntity extends SoftDeleteEntity {

    @Column(nullable = false)
    private Boolean active = true;
}
