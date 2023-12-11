package com.example.metronome_server.controllers;

import com.example.metronome_server.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

}
