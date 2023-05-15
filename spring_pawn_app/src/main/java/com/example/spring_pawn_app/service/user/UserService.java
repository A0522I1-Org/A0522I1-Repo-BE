package com.example.spring_pawn_app.service.user;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public User findByIdEmployee(Integer id) {
        return iUserRepository.findByEmployee(new Employee(id));
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }
}
