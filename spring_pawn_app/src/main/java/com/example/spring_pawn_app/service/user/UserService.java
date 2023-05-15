package com.example.spring_pawn_app.service.user;

import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
