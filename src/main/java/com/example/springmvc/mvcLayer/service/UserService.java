package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByUsername(String username);

    User saveUser(User user);

    Page<User> findAllByPage(Pageable pageRequest);

    void setEnable(Long userId, Boolean enable);
}
