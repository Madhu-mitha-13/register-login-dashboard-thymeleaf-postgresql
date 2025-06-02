package com.ramacciotti.registerapp.controller;

import com.ramacciotti.registerapp.dto.AuthRequest;
import com.ramacciotti.registerapp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        log.info("Exibindo formulário de registro...");
        model.addAttribute("authRequest", new AuthRequest());
        return "register";  // nome do template register.html
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute AuthRequest authRequest, Model model) {
        log.info("Processando registro para o usuário: {}", authRequest.getUsername());

        try {
            userServiceImpl.createUser(authRequest.getUsername(), authRequest.getPassword());
            model.addAttribute("message", "Usuário registrado com sucesso!");
            log.info("Usuário registrado com sucesso: {}", authRequest.getUsername());
            return "redirect:/auth/login";
        } catch (RuntimeException e) {
            model.addAttribute("message", "Erro: " + e.getMessage());
            log.warn("Erro ao registrar usuário {}: {}", authRequest.getUsername(), e.getMessage());
        }

        model.addAttribute("authRequest", new AuthRequest());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        log.info("Exibindo formulário de login...");
        model.addAttribute("authRequest", new AuthRequest());
        return "login";  // nome do template login.html
    }

}
