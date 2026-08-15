package com.roochi.petflowidentity.otp.service;

import com.roochi.petflowidentity.otp.entity.Otp;
import com.roochi.petflowidentity.otp.finder.OtpFinder;
import com.roochi.petflowidentity.otp.generator.OtpGenerator;
import com.roochi.petflowidentity.otp.repository.OtpRepository;
import com.roochi.petflowidentity.otp.validator.OtpValidator;
import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final OtpFinder finder;
    private final OtpValidator validator;
    private final OtpGenerator generator;

    @Override
    public Otp generate(String mobile) {
        validateCanSend(mobile);
        otpRepository.expireAllByMobile(mobile);
        String code =
                generator.generate();
        Otp otp = Otp.builder()
                .mobile(mobile)
                .code(code)
                .used(false).build();
        return otpRepository.save(otp);
    }

    @Override
    @Transactional(readOnly = true)
    public void validateCanSend(String mobile) {
        finder.findLatestByMobile(mobile).ifPresent(
                validator::validateCanSend);
    }

    @Override
    public Otp verify(String mobile, String code) {
        Otp otp = otpRepository.findTopByMobileAndCodeOrderByCreatedAtDesc(mobile, code)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
        if (Boolean.TRUE.equals(otp.isUsed()))
            throw new AlreadyExistsException(ErrorCode.CLINIC_NOT_FOUND);
        if (otp.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new AlreadyExistsException(ErrorCode.CLINIC_NOT_FOUND);
        consume(otp);
        return otp;
    }

    @Override
    public void consume(Otp otp) {
        otp.setUsed(true);
        otpRepository.save(otp);
    }

    @Override
    public String generateOtp() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999));
    }
}
