package com.ibrplanner.dslist.controllers;

import com.ibrplanner.dslist.dtos.GameDTO;
import com.ibrplanner.dslist.dtos.GameFullFieldDTO;
import com.ibrplanner.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/{id}")
    public GameFullFieldDTO findById(@PathVariable Long id) {
        GameFullFieldDTO result = gameService.findById(id);
        return result;
    }

    @GetMapping
    public List<GameDTO> findAll() {
        List<GameDTO> result = gameService.findAll();
        return result;
    }


}
