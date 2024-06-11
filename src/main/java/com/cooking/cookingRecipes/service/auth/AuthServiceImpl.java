package com.cooking.cookingRecipes.service.auth;

import com.cooking.cookingRecipes.config.JwtService;
import com.cooking.cookingRecipes.controller.auth.AuthService;
import com.cooking.cookingRecipes.entity.auth.AuthenticationRequest;
import com.cooking.cookingRecipes.entity.auth.AuthenticationResponse;
import com.cooking.cookingRecipes.entity.auth.RegisterRequest;
import com.cooking.cookingRecipes.entity.user.Role;
import com.cooking.cookingRecipes.entity.user.User;
import com.cooking.cookingRecipes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userService.findUserByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.USER)
                .build();
        userService.saveUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
