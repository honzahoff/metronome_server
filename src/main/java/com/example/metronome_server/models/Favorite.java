package com.example.metronome_server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    @ManyToOne
    private @Setter User user;

    private @Setter String name;

    private @Setter String signature;

    @ManyToOne
    private @Setter Sound sound;

    private @Setter Integer tempo;


}
