package com.example.spring_pawn_app.service.user;
import com.example.spring_pawn_app.model.Employee;
import org.springframework.stereotype.Service;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    @Autowired
    private IUserRepository iUserRepository;


    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    }

//    @Override
//    public User save(User user) {
//        return iUserRepository.existsByUserName(username);
//    }


    @Override
    public User findByIdEmployee(Integer id) {
        return iUserRepository.findByEmployee(new Employee(id));
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmployee(Employee employee) {
        return userRepository.findUserByEmployee(employee);
    }


}
