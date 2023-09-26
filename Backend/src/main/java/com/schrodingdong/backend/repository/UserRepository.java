package com.schrodingdong.backend.repository;

import com.schrodingdong.backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {
}
