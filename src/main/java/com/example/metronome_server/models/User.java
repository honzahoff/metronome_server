package com.example.metronome_server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    private @Setter String name;

    private @Setter String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="user-favorites")
    private @Setter Set<Favorite> favorites;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-settings")
    private @Setter Settings settings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="user-sessions")
    private @Setter Set<Session> sessions;
}
