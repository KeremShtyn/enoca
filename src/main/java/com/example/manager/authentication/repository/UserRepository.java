package com.example.manager.authentication.repository;


import com.example.manager.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>, CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
