package com.example.springmvc.mvcLayer.service.impl.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.repository.UserRepository;
import com.example.springmvc.mvcLayer.service.RoleService;

import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.findByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindByUsername2() {
        when(this.userRepository.findByUsername((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userRepository.save((User) any())).thenReturn(user);

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        when(this.roleService.findByName((String) any())).thenReturn(role);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setEmail("jane.doe@example.org");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setRoles(new ArrayList<Role>());
        assertSame(user, this.userServiceImpl.saveUser(user1));
        verify(this.userRepository).save((User) any());
        verify(this.roleService).findByName((String) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        assertEquals(1, user1.getRoles().size());
        assertEquals("secret", user1.getPassword());
    }

    @Test
    void testFindAllByPage() {
        PageImpl<User> pageImpl = new PageImpl<User>(new ArrayList<User>());
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        Page<User> actualFindAllByPageResult = this.userServiceImpl.findAllByPage(null);
        assertSame(pageImpl, actualFindAllByPageResult);
        assertTrue(actualFindAllByPageResult.toList().isEmpty());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testSetEnable() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        Optional<User> ofResult = Optional.<User>of(user);

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setEmail("jane.doe@example.org");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setRoles(new ArrayList<Role>());
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        this.userServiceImpl.setEnable(123L, true);
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testSetEnable2() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(EntityNotFoundException.class, () -> this.userServiceImpl.setEnable(123L, true));
        verify(this.userRepository).findById((Long) any());
    }
}

