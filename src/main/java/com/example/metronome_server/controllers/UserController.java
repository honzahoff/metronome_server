package com.example.metronome_server.controllers;

import com.example.metronome_server.exceptions.UserNotFoundException;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<User> addNewUser (@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/settings")
    public @ResponseBody ResponseEntity<User> updateSettings(@PathVariable Integer id, @RequestBody Settings settings) {
        try {
            User user = userService.updateSettings(id, settings);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public @ResponseBody Iterable<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User> getUser (@PathVariable Integer id){
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser (@PathVariable Integer id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("Removed");
        } catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
}
