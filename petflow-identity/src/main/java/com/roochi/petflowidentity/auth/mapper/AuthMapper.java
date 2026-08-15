package com.roochi.petflowidentity.auth.mapper;

import com.roochi.petflowidentity.auth.dto.response.LoginResponseDto;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.entity.UserClinic;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
public class AuthMapper {

    public LoginResponseDto toLoginResponseDto(User user, UserClinic userClinic, String accessToken,
                                               String refreshToken){
        return LoginResponseDto.builder()
                .build();
    }
}
