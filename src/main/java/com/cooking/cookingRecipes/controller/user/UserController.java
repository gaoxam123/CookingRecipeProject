package com.cooking.cookingRecipes.controller.user;


import com.cooking.cookingRecipes.entity.user.Role;
import com.cooking.cookingRecipes.entity.user.User;
import com.cooking.cookingRecipes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
@CrossOrigin("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public String index(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/{userId}/edit")
    public String editUser(Model model, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PutMapping(path = "/{userId}")
    public String updateUser(@PathVariable Long userId, User user) {
        User targetUser = userService.getUserById(userId);
        Role currentRole = targetUser.getRole();
        BeanUtils.copyProperties(user, targetUser, "id");
        targetUser.setRole(currentRole);
        userService.updateUser(targetUser);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/{userId}")
    public String show(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/show";
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable Long userId) {
        if (userService.getUserById(userId) == null) {
            throw new NoSuchElementException("Cannot find user with id " + userId);
        }
        userService.deleteUser(userId);
        return "redirect:/users/list";
    }
}
