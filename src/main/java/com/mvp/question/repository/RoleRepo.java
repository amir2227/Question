package com.mvp.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.question.models.Role;
import com.mvp.question.models.enums.ERole;


@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}

