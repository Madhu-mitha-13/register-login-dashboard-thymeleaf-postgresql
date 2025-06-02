package com.ramacciotti.registerapp.controller;

import com.ramacciotti.registerapp.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserServiceImpl userService;

    public DashboardController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        log.info("Usuário '{}' acessou o dashboard!", username);
        model.addAttribute("email", username);
        return "dashboard";
    }

    @PostMapping("/change-email")
    public String changeEmail(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newEmail, HttpServletRequest request) {
        userService.updateUserEmail(userDetails.getUsername(), newEmail);
        log.info("Usuário '{}' alterou o email para '{}'", userDetails.getUsername(), newEmail);
        request.getSession().invalidate();
        return "redirect:/auth/login?emailChanged";
    }

    @PostMapping("/change-password")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newPassword, HttpServletRequest request) {
        userService.updateUserPassword(userDetails.getUsername(), newPassword);
        log.info("Usuário '{}' alterou a senha.", userDetails.getUsername());
        request.getSession().invalidate();
        return "redirect:/auth/login?passwordChanged";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        userService.deleteUserByUsername(username);
        log.info("Usuário '{}' deletou sua conta.", username);
        return "redirect:/logout";
    }


}

