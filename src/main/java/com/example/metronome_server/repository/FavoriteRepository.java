package com.example.metronome_server.repository;

import com.example.metronome_server.model.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
}
