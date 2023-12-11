package com.example.metronome_server.repositories;

import com.example.metronome_server.models.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
}
