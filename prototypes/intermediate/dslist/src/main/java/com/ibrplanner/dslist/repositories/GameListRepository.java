package com.ibrplanner.dslist.repositories;

import com.ibrplanner.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}