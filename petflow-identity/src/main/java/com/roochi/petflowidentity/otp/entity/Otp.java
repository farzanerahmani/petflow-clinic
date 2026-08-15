package com.roochi.petflowidentity.otp.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import com.roochi.petflowshared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Entity
@Table(name = "otps")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Otp extends AuditingEntity {

    @Column(nullable = false, length = 11)
    private String mobile;

    @Column(nullable = false, length = 6)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Column(nullable = false)
    private boolean used = false;

    @Column(nullable = false)
    private int failedAttempts = 0;
}
