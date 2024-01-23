package com.example.metronome_server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private @Setter Integer id;

    private @Setter LocalDateTime startTime;
    private @Setter LocalDateTime endTime;

    @ManyToOne
    @JsonBackReference(value="user-sessions")
    private @Setter User user;
}
