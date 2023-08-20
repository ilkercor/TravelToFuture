package com.amadeusproject.TravelToFuture.DataAccess.User;

import com.amadeusproject.TravelToFuture.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
