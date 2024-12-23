package in.coder.abhijit.demo.service;

import org.springframework.stereotype.Service;

import in.coder.abhijit.demo.entity.User;
import in.coder.abhijit.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User saveUser(User user){
        return userRepository.save(user);
    }


}
