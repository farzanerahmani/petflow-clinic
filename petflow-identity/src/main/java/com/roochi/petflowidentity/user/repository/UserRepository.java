package com.roochi.petflowidentity.user.repository;

import com.roochi.petflowidentity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByMobile(String mobile);

    boolean existsByMobile(String mobile);

}
