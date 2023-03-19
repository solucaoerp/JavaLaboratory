package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.StatusEntrega;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        /*
            Regras aqui:
            - regras de horário
            - validações para entregadores disponíveis
        */

        Cliente cliente = clienteService.findById(entrega.getCliente().getId());

        entrega.setCliente(cliente); /* faz com que o retorno seja preenchido sem null */
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }
}
