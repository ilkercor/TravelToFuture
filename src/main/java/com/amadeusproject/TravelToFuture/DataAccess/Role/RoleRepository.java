package com.amadeusproject.TravelToFuture.DataAccess.Role;

import com.amadeusproject.TravelToFuture.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
