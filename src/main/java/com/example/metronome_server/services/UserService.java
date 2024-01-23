package com.example.metronome_server.services;

import com.example.metronome_server.exceptions.EntityNotFoundException;
import com.example.metronome_server.models.Favorite;
import com.example.metronome_server.models.Session;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    public User updateName(Integer id, String name) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(name);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }


    public User updateSettings(Integer id, Settings settings) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Settings currentSettings = user.getSettings();

            if (currentSettings == null) {
                currentSettings = settings;
                user.setSettings(currentSettings);
            }

            currentSettings.setHapticFeedback(settings.getHapticFeedback());
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
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
                throw new EntityNotFoundException("User with id " + id + " not found");
            }
    }

    public User addSession(Integer id, Session session) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            session.setUser(user);
            user.getSessions().add(session);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public User findByEmail(String email) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
    }

    public Set<Favorite> getFavorites(Integer userId){
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            return user.getFavorites();
        } else {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
    }

    public void removeFavorite(Integer userId, Integer favoriteId) throws EntityNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Optional<Favorite> optionalFavorite = user.getFavorites().stream().filter(fav -> fav.getId().equals(favoriteId)).findFirst();

            if (optionalFavorite.isPresent()){
                Favorite favorite = optionalFavorite.get();
                favorite.setSound(null);
                favorite.setUser(null);
                user.getFavorites().remove(favorite);
                favoriteService.deleteById(favoriteId);

            } else {
                throw new EntityNotFoundException("Favorite with id " + favoriteId + " not found");
            }
        } else {
            throw new EntityNotFoundException("User with id " + userId + " not found");

        }
    }
    public void deleteById(Integer id) throws EntityNotFoundException{
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email) throws EntityNotFoundException{
        if (!userRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("User with email " + email + " not foun");
        }
        userRepository.deleteByEmail(email);
    }
}
