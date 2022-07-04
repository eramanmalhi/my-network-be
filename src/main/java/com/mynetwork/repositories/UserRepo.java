package com.mynetwork.repositories;

import com.mynetwork.entities.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> getByUserName(String userName);

    Optional<UserLogin> findByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);
}
