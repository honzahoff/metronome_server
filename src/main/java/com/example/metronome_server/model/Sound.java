package com.example.metronome_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Sound {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    @OneToMany(mappedBy = "sound")
    private List<Favorite> favorites;

    private @Setter String name;

    private @Setter String version;

    private @Setter String first_beat_sound_path;

    private @Setter String other_beat_sound_path;
}
