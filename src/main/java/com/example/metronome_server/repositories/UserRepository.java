package com.example.metronome_server.repositories;

import com.example.metronome_server.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

}
