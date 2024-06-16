package com.cooking.cookingRecipes.controller.auth;

import com.cooking.cookingRecipes.entity.auth.AuthenticationRequest;
import com.cooking.cookingRecipes.entity.auth.AuthenticationResponse;
import com.cooking.cookingRecipes.entity.auth.RegisterRequest;
import com.cooking.cookingRecipes.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("request", new AuthenticationRequest());
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("request", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping(path = "/save")
    public ResponseEntity<AuthenticationResponse> registerUser(@ModelAttribute RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@ModelAttribute AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
