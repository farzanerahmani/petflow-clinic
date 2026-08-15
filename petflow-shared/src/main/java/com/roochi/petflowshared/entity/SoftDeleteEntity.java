package com.roochi.petflowshared.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 6/26/2026
 */
@MappedSuperclass
@Getter
@Setter
public abstract class SoftDeleteEntity extends AuditingEntity {
    @Column(nullable = false)
    private Boolean deleted = false;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private Long deletedBy;
}
