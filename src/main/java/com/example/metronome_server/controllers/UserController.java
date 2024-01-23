package com.example.metronome_server.controllers;

import com.example.metronome_server.exceptions.EntityNotFoundException;
import com.example.metronome_server.models.Favorite;
import com.example.metronome_server.models.Session;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<User> addNewUser (@RequestBody User user) {
        try {
            User newUser =  userService.save(user);
            return ResponseEntity.ok(newUser);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/name")
    public @ResponseBody ResponseEntity<User> updateName(@PathVariable Integer id, @RequestParam String name){
        try {
            User user = userService.updateName(id, name);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/settings")
    public @ResponseBody ResponseEntity<User> updateSettings(@PathVariable Integer id, @RequestBody Settings settings) {
        try {
            User user = userService.updateSettings(id, settings);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/favorites")
    public @ResponseBody ResponseEntity<User> addFavorite(@PathVariable Integer id, @RequestBody Favorite favorite){
        try {
            User user = userService.addFavorite(id, favorite);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/sessions")
    public @ResponseBody ResponseEntity<User> addSession(@PathVariable Integer id, @RequestBody Session session){
        try {
            User user = userService.addSession(id, session);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
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
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/favorites")
    public @ResponseBody ResponseEntity<Set<Favorite>> getFavorites (@PathVariable Integer id){
        try {
            Set<Favorite> favorites = userService.getFavorites(id);
            return ResponseEntity.ok(favorites);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/getByEmail/{email}")
    public @ResponseBody ResponseEntity<User> getUserByEmail (@PathVariable String email){
        try {
            User user = userService.findByEmail(email);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/favorites/{favoriteId}")
    public @ResponseBody ResponseEntity<String> removeFavorite(@PathVariable Integer userId, @PathVariable Integer favoriteId) {
        try {
            userService.removeFavorite(userId, favoriteId);
            return ResponseEntity.ok("Removed");
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser (@PathVariable Integer id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("Removed");
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/deleteByEmail/{email}")
    public @ResponseBody ResponseEntity<String> deleteUserByEmail (@PathVariable String email){
        try {
            userService.deleteByEmail(email);
            return ResponseEntity.ok("Removed");
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
