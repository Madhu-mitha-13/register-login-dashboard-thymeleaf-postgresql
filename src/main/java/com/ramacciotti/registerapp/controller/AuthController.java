package com.ramacciotti.registerapp.controller;
import com.ramacciotti.registerapp.dto.AuthRequest;
import com.ramacciotti.registerapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")  // Use esse caminho para web
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("authRequest", new AuthRequest());
        return "register";  // nome do template register.html
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute AuthRequest authRequest, Model model) {
        try {
            userServiceImpl.registerUser(authRequest.getUsername(), authRequest.getPassword());
            model.addAttribute("message", "Usuário registrado com sucesso!");
        } catch (RuntimeException e) {
            model.addAttribute("message", "Erro: " + e.getMessage());
        }
        model.addAttribute("authRequest", new AuthRequest());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("authRequest", new AuthRequest());
        return "login";  // nome do template login.html
    }

}
