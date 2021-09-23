package com.example.springmvc.mvcLayer.service.impl.security;

import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.repository.RoleRepository;
import com.example.springmvc.mvcLayer.service.RoleService;
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
