package com.example.metronome_server.repository;

import com.example.metronome_server.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
