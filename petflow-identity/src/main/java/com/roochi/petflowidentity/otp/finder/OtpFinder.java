package com.roochi.petflowidentity.otp.finder;

import com.roochi.petflowidentity.otp.entity.Otp;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface OtpFinder {

    Optional<Otp> findLatestByMobile(String mobile);

    Otp findValidOtp(String mobile,String code);
}
