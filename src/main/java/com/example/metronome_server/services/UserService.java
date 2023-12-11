package com.example.metronome_server.services;

import com.example.metronome_server.exceptions.UserNotFoundException;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User updateSettings(Integer id, Settings settings){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            settings.setUser(user);
            user.setSettings(settings);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User with id " + id + "not found");
        }
    }

    public User findById(Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new UserNotFoundException("User with id " + id + "not found");
        }
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteById(Integer id){
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
