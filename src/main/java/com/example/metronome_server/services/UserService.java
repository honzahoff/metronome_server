package com.example.metronome_server.services;

import com.example.metronome_server.exceptions.EntityNotFoundException;
import com.example.metronome_server.models.Favorite;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final FavoriteService favoriteService;

    public UserService(UserRepository userRepository, FavoriteService favoriteService){
        this.userRepository = userRepository;
        this.favoriteService = favoriteService;
    }

    public User save(User user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new Exception("User already created!");
        } else {
            return userRepository.save(user);
        }
    }

    public User updateUsername(Integer id, String name) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(name);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + "not found");
        }
    }
    public User updateEmail(Integer id, String email) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(email);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + "not found");
        }
    }


    public User updateSettings(Integer id, Settings settings) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            settings.setUser(user);
            user.setSettings(settings);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + "not found");
        }
    }

    public User addFavorite(Integer id, Favorite favorite) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            favorite.setUser(user);
            user.getFavorites().add(favorite);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + "not found");
        }
    }

    public User findById(Integer id) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User with id " + id + "not found");
        }
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User removeFavorite(Integer userId, Integer favoriteId) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Optional<Favorite> optionalFavorite = user.getFavorites().stream().filter(fav -> fav.getId().equals(favoriteId)).findFirst();

            if (optionalFavorite.isPresent()){
                Favorite favorite = optionalFavorite.get();
                user.getFavorites().remove(favorite);
                userRepository.save(user);
                favoriteService.deleteById(favoriteId);
                return user;
            } else {
                throw new EntityNotFoundException("Favorite with id " + favoriteId + "not found");
            }
        } else {
            throw new EntityNotFoundException("User with id " + userId + "not found");

        }
    }
    public void deleteById(Integer id) throws EntityNotFoundException{
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
