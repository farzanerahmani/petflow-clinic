package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class UserFinderImpl implements UserFinder{
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findByMobile(String mobile) {
        return userRepository.findByMobile(mobile).orElseThrow(() ->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
    @Override
    public boolean existsByMobile(String mobile){
        return userRepository.existsByMobile(mobile);
    }
}
