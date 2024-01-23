package com.example.metronome_server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference(value="user-favorites")
    private @Setter User user;

    private @Setter String name;

    private @Setter String signature;

    private @Setter String sound;

    private @Setter Integer tempo;
}
