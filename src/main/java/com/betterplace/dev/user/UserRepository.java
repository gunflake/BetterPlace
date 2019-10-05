package com.betterplace.dev.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserIDAndPassword (String id, String password);
}
