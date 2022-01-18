package com.example.springmvc.mvcLayer.controller;

import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.springmvc.mvcLayer.domain.constans.ConstanceName.*;

@Controller
@AllArgsConstructor
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @GetMapping(ADMIN)
    public String getAllUsers(@RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;
        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<User> page = userService.findAllByPage(pageRequest);
        model.addAttribute("page", page);
        return "user/admin";
    }

    @GetMapping(REGISTRATION)
    public String createModelRegistration(Model model) {
        model.addAttribute("user", new User());
        return "user/registration";
    }

    @PostMapping(REGISTRATION)
    public String registrationUser(User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping(ENABLE)
    public String setEnableUser(@RequestParam Long userId, @RequestParam Boolean enable) {
        userService.setEnable(userId, enable);
        return "redirect:/user/admin";
    }

    @GetMapping(ACCOUNT)
    public String getUserAccount(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user);
        return "user/personalAccount";
    }
}