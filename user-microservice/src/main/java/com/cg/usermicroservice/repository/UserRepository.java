package com.cg.usermicroservice.repository;

import com.cg.usermicroservice.entity.User;
import com.cg.usermicroservice.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
