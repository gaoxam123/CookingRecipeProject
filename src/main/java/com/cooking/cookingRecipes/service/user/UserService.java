package com.cooking.cookingRecipes.service.user;

import com.cooking.cookingRecipes.entity.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getUsers();
    User getUserById(UUID id);
    void deleteUser(UUID id);
    void updateUser(User user);
    void register(User user);
//    void login(User user);
//    void logout();
}
