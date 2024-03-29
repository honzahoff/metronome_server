package com.example.metronome_server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    private @Setter Boolean hapticFeedback;

    @OneToOne(mappedBy = "settings")
    @JsonBackReference(value="user-settings")
    private @Setter User user;
}
