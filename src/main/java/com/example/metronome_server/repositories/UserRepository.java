package com.example.metronome_server.repositories;

import com.example.metronome_server.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);
}
