package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.User;

/**
 * @author farzane.rahmani
 * @created 7/1/2026
 */
public interface UserFinder {

    User findById(Long id);

    User findByMobile(String mobile);

    boolean existsByMobile(String mobile);


}
