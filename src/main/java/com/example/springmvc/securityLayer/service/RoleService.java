package com.example.springmvc.securityLayer.service;

import com.example.springmvc.securityLayer.domain.Role;

public interface RoleService {

    Role findByName(String name);
}