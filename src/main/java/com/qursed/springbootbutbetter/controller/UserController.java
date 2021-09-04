package com.qursed.springbootbutbetter.controller;

import com.qursed.springbootbutbetter.model.User;
import com.qursed.springbootbutbetter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getStartPage() {
        return "redirect:/login";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("user")
    public String getUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("currentUser", user);
        return "adminPanel";
    }

    @GetMapping("admin")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("addUser", new User());
        model.addAttribute("currentUser", user);
        model.addAttribute("userList", service.getAllUsers());
        model.addAttribute("allRoles", service.getAllRoles());
        return "adminPanel";
    }

    @PostMapping("admin/add")
    public String addUser(@ModelAttribute User user) {
        service.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/edit")
    public String editUser(@ModelAttribute User user) {
        service.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }
}
