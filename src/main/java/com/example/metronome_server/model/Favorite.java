package com.example.metronome_server.model;

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
    private User user;

    private @Setter @Getter String name;

    private @Setter @Getter String signature;

    @ManyToOne
    private @Setter @Getter Sound sound;

    private @Setter @Getter Integer tempo;


}
