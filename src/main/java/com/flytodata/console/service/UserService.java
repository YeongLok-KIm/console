package com.flytodata.console.service;

import com.flytodata.console.entity.User;
import com.flytodata.console.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long user_id) {
        return this.userRepository.findUserById(user_id);
    }

    public User findUserByUserCode(String user_code){
        return this.userRepository.findUserByUserCode(user_code);
    }

    public User findUserByEmail(String email){
        return this.userRepository.findUserByEmail(email);
    }

    public User save(User user){
        return this.userRepository.save(user);
    }

}
