package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Cliente;
import com.ibrplanner.pedidos.repositories.ClienteRepository;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}