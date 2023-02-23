package com.ibrplanner.laboratory.repositories;

import com.ibrplanner.laboratory.entities.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
