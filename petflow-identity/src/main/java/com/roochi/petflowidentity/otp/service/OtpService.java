package com.roochi.petflowidentity.otp.service;

import com.roochi.petflowidentity.otp.entity.Otp;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface OtpService {
    Otp generate(String mobile);
    void validateCanSend(String mobile);
    Otp verify(String mobile ,String code);

    void consume(Otp otp);

    String generateOtp();
}
