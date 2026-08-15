package com.roochi.petflowidentity.otp.finder;

import com.roochi.petflowidentity.otp.entity.Otp;
import com.roochi.petflowidentity.otp.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class OtpFinderImpl implements OtpFinder {

    private final OtpRepository repository;

    @Override
    public Optional<Otp> findLatestByMobile(String mobile) {
        return repository.findTopByMobileOrderByCreatedAtDesc(mobile);
    }

    @Override
    public Otp findValidOtp(String mobile, String code) {
        return repository.findByMobileAndCodeAndUsedFalse(mobile, code)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
