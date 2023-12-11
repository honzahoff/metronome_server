package com.example.metronome_server.services;

import com.example.metronome_server.exceptions.EntityNotFoundException;
import com.example.metronome_server.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }

    public void deleteById(Integer id) throws EntityNotFoundException{
        if (!favoriteRepository.existsById(id)) {
            throw new EntityNotFoundException("Favorite with id " + id + " not found");
        }
        favoriteRepository.deleteById(id);
    }

}
