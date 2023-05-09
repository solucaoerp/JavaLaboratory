package com.ibrplanner.dslist.repositories;

import com.ibrplanner.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
