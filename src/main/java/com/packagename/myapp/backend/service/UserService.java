package com.packagename.myapp.backend.service;


import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.entity.User;
import org.springframework.stereotype.Service;
import com.packagename.myapp.backend.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public long count() {
        return userRepository.count();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        if (user == null) {
            LOGGER.log(Level.SEVERE,
                    "User is null. Are you sure you have connected your form to the application?");
            return;
        }
        userRepository.save(user);
    }
    //
    //to add data loader
    //
    @PostConstruct
    public void populateTestData() {
        if (userRepository.count() == 0) {
            int i = 0;
            Random r = new Random(0);
            userRepository.saveAll(
                    Stream.of("yradwan", "admin", "denis")
                            .map(name -> {
                                User user = new User();
                                user.setUserName(name);
                                user.setPassword("password");
                                return user;
                            }).collect(Collectors.toList()));
        }
    }
}
