package com.ibrplanner.dslist.controllers;

import com.ibrplanner.dslist.dtos.GameDTO;
import com.ibrplanner.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameDTO> findAll() {
        List<GameDTO> result = gameService.findAll();
        return result;
    }

}
