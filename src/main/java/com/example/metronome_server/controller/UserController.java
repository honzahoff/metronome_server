package com.example.metronome_server.controller;

import com.example.metronome_server.model.User;
import com.example.metronome_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public @ResponseBody String addNewUser (@RequestParam String username) {
        User n = new User();
        n.setUserName(username);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<User> getUser (@PathVariable Integer id){
        return userRepository.findById(id);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody String deleteUser (@PathVariable Integer id){
        userRepository.deleteById(id);
        return "Removed";
    }
}
