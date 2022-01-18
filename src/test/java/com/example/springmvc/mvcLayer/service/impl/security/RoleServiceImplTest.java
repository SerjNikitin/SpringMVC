package com.example.springmvc.mvcLayer.service.impl.security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.repository.RoleRepository;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Test
    void testFindByName() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        Optional<Role> ofResult = Optional.<Role>of(role);
        when(this.roleRepository.findByNameIgnoreCase((String) any())).thenReturn(ofResult);
        assertSame(role, this.roleServiceImpl.findByName("Name"));
        verify(this.roleRepository).findByNameIgnoreCase((String) any());
    }

    @Test
    void testFindByName2() {
        when(this.roleRepository.findByNameIgnoreCase((String) any())).thenReturn(Optional.<Role>empty());
        assertThrows(EntityNotFoundException.class, () -> this.roleServiceImpl.findByName("Name"));
        verify(this.roleRepository).findByNameIgnoreCase((String) any());
    }
}

