package com.example.springmvc.securityLayer.repository;

import com.example.springmvc.securityLayer.domain.Role;
import com.example.springmvc.securityLayer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNameIgnoreCase(String name);
}
