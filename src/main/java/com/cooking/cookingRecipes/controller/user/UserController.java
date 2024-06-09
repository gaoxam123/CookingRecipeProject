package com.cooking.cookingRecipes.controller.user;


import com.cooking.cookingRecipes.entity.user.User;
import com.cooking.cookingRecipes.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/register")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/")
    public String registerUser(@RequestBody User user) {
        userService.register(user);
        return "redirect:/users/" + user.getId();
    }

    @GetMapping("/edit")
    public String editUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable UUID userId, @RequestBody User user) {
        User targetUser = userService.getUserById(userId);
        BeanUtils.copyProperties(user, targetUser, "id");
        userService.updateUser(targetUser);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/{userId}")
    public String show(@PathVariable UUID userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/show";
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable UUID userId) {
        if (userService.getUserById(userId) == null) {
            throw new NoSuchElementException("Cannot find user with id " + userId);
        }
        userService.deleteUser(userId);
        return "redirect:/users";
    }
}
