package com.example.metronome_server.repositories;

import com.example.metronome_server.models.Settings;
import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<Settings, Integer> {
}
