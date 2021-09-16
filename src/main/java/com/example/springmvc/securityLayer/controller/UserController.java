package com.example.springmvc.securityLayer.controller;

import com.example.springmvc.securityLayer.domain.User;
import com.example.springmvc.securityLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/admin")
    public String getAllUsers(@RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<User> page = userService.findAllByPage(pageRequest);

        model.addAttribute("page", page);

        return "user/admin";
    }

    @GetMapping("/registration")
    public String createModelRegistration(Model model) {
        model.addAttribute("user", new User());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/enable")
    public String setEnableUser(@RequestParam Long userId, @RequestParam Boolean enable) {
        userService.setEnable(userId, enable);

        return "redirect:/user/admin";
    }
}