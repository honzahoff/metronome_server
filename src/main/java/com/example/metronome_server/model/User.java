package com.example.metronome_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Integer id;

    private @Getter @Setter String userName;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;


}
