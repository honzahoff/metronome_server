package com.example.metronome_server.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    private @Setter String username;

    private @Setter String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private @Setter List<Favorite> favorites;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private @Setter Settings settings;


}
