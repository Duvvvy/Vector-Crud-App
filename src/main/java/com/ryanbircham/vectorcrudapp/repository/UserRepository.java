package com.ryanbircham.vectorcrudapp.repository;

import com.ryanbircham.vectorcrudapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
}
