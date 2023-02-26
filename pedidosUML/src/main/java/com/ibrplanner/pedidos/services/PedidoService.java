package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Pedido;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import com.ibrplanner.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}
