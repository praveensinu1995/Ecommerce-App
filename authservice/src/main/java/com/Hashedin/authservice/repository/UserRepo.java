package com.Hashedin.authservice.repository;

import com.Hashedin.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String name);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
