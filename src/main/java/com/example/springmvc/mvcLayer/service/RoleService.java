package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.security.Role;

public interface RoleService {

    Role findByName(String name);
}