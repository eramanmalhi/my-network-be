package com.mynetwork.repositories;

import com.mynetwork.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserName(String userName);
}
