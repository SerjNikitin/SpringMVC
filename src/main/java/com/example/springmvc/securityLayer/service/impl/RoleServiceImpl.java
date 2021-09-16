package com.example.springmvc.securityLayer.service.impl;

import com.example.springmvc.securityLayer.domain.Role;
import com.example.springmvc.securityLayer.repository.RoleRepository;
import com.example.springmvc.securityLayer.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Optional<Role> byNameIgnoreCase = roleRepository.findByNameIgnoreCase(name);
        if (byNameIgnoreCase.isEmpty()) {
            throw new EntityNotFoundException("Role witch name" + name + " not found");
        }
        return byNameIgnoreCase.get();
    }
}
