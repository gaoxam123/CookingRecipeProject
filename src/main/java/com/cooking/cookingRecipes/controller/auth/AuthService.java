package com.cooking.cookingRecipes.controller.auth;

import com.cooking.cookingRecipes.entity.auth.AuthenticationRequest;
import com.cooking.cookingRecipes.entity.auth.AuthenticationResponse;
import com.cooking.cookingRecipes.entity.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
}
