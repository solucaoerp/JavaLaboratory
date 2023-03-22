package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.Ocorrencia;
import com.ibrplanner.logistica.exceptions.exceptionService.EntidadeNaoEncontradaException;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private EntregaRepository entregaRepository;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscarPorId(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

    public Entrega buscarPorId(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }
}
