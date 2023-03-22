package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private EntregaService entregaService;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = entregaService.buscar(entregaId);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}
