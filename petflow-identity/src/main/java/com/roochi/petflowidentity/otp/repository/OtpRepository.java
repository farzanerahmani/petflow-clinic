package com.roochi.petflowidentity.otp.repository;

import com.roochi.petflowidentity.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByMobileOrderByCreatedAtDesc(String mobile);

    Optional<Otp> findByMobileAndCodeAndUsedFalse(String mobile, String code);

    Optional<Otp> findByMobileAndCode(String mobile, String code);

    void deleteByMobile(String mobile);

    Optional<Otp> findTopByMobileAndCodeOrderByCreatedAtDesc(String mobile, String code);

    @Modifying
    @Query(value = "UPDATE Otp o SET o.used = true" +
            " WHERE o.mobile =:mobile and o.used= false")
    void expireAllByMobile(String mobile);
}
