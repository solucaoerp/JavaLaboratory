package com.ibrplanner.dslist.services;

import com.ibrplanner.dslist.dtos.GameDTO;
import com.ibrplanner.dslist.dtos.GameFullFieldDTO;
import com.ibrplanner.dslist.entities.Game;
import com.ibrplanner.dslist.projections.GameMinProjection;
import com.ibrplanner.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameFullFieldDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();

        GameFullFieldDTO dto = new GameFullFieldDTO(result);

        return dto;
    }

    @Transactional(readOnly = true)
    public List<GameDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        List<GameDTO> dto = result.stream().map(x -> new GameDTO(x)).toList();
        return dto;
    }
    @Transactional(readOnly = true)
    public List<GameDTO> findByList(Long listId) {
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        List<GameDTO> dto = result.stream().map(x -> new GameDTO(x)).toList();
        return dto;
    }
}
