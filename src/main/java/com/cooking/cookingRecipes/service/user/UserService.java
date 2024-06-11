package com.cooking.cookingRecipes.service.user;

import com.cooking.cookingRecipes.entity.user.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    void register(User user);
//    void login(User user);
//    void logout();
}
