package com.gxuwz.hospital.repository;

import com.gxuwz.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

    User findByUsername(String username);
}
