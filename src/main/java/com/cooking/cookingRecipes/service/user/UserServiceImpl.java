package com.cooking.cookingRecipes.service.user;

import com.cooking.cookingRecipes.dao.UserRepository;
import com.cooking.cookingRecipes.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        User result = null;
        if (user.isPresent()) {
            result = user.get();
        }
        else {
            throw new RuntimeException("Can't find user with id: " + id);
        }
        return result;
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }
}
