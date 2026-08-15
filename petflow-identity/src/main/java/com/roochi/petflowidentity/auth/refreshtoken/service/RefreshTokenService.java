package com.roochi.petflowidentity.auth.refreshtoken.service;

import com.roochi.petflowidentity.auth.refreshtoken.entity.RefreshToken;
import com.roochi.petflowidentity.user.entity.User;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public interface RefreshTokenService {

    /**
     * ایجاد refresh token جدید
     */
    RefreshToken create(User user);

    /**
     * اعتبار سنجی refresh token
     */
    RefreshToken verify(String token);

    /**
     * ابطال یک refresh token
     */
    void revoke(String token);

    /**
     * ابطال تمام refresh token های کاربر
     */
    void revokeAll(Long userId);
}
