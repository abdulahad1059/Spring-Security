package com.springlearning.springlearning.api;

import com.springlearning.springlearning.Model.User;
import com.springlearning.springlearning.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/students")
    List<User> getAllUser() {
        return userRepository.findAll();
    }


    @GetMapping("/students/{id}")
    Object getUser(@PathVariable String id) {
        Long id1 = Long.parseLong(id);
        System.out.print(id1);
        return userRepository.findById(id1).get();
    }
}
