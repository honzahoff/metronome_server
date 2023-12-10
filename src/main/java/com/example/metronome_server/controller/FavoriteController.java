package com.example.metronome_server.controller;

import com.example.metronome_server.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

}
