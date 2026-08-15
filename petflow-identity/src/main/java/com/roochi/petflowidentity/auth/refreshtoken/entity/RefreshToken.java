package com.roochi.petflowidentity.auth.refreshtoken.entity;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "auth_refresh_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken extends BaseEntity {

    @Column(nullable = false, unique = true, length = 500)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column
    private LocalDateTime revokedAt;

    @Column
    private LocalDateTime lastUsedAt;

    @Column(length = 100)
    private String deviceId;

    @Column(length = 45)
    private  String ipAddress;

    @Column(length = 500)
    private String userAgent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientType clientType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    /**
     * آیا توکن منقضی شده است؟
     */
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }

    /**
     * آیا توکن باطل شده است؟
     */
    public boolean isRevoked() {
        return revokedAt != null;
    }

    /**
     * باطل کردن توکن
     */
    public void revoke() {
        this.revokedAt = LocalDateTime.now();
    }

    public void updateLastUsed(){
        this.lastUsedAt = LocalDateTime.now();
    }
}
