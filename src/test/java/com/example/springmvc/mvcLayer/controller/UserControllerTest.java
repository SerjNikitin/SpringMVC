package com.example.springmvc.mvcLayer.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.service.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testCreateModelRegistration() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/registration");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("user/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("user/registration"));
    }

    @Test
    void testCreateModelRegistration2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/registration", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("user/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("user/registration"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(this.userService.findAllByPage((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<User>(new ArrayList<User>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/admin");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("page"))
                .andExpect(MockMvcResultMatchers.view().name("user/admin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("user/admin"));
    }

    @Test
    void testGetAllUsers2() throws Exception {
        when(this.userService.findAllByPage((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<User>(new ArrayList<User>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/admin");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("pageNum", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("page"))
                .andExpect(MockMvcResultMatchers.view().name("user/admin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("user/admin"));
    }

    @Test
    void testRegistrationUser() throws Exception {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userService.saveUser((User) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/registration");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    void testSetEnableUser() throws Exception {
        doNothing().when(this.userService).setEnable((Long) any(), (Boolean) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/enable");
        MockHttpServletRequestBuilder paramResult = getResult.param("enable", String.valueOf(true));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/user/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/admin"));
    }
}

