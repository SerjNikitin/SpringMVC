package com.example.springmvc.securityLayer.service.impl;

import com.example.springmvc.securityLayer.domain.Role;
import com.example.springmvc.securityLayer.domain.User;
import com.example.springmvc.securityLayer.repository.UserRepository;
import com.example.springmvc.securityLayer.service.RoleService;
import com.example.springmvc.securityLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return byUsername.get();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        Role userRole = roleService.findByName("ROLE_USER");
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void setEnable(Long userId, Boolean enable) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("User witch id" + userId + " not found");
        }
        User user = byId.get();
        user.setEnabled(enable);
        userRepository.save(user);
    }
}
