package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.common.ObjectConverter;
import com.ibrplanner.logistica.dtos.DestinatarioDTO;
import com.ibrplanner.logistica.dtos.EntregaDTO;
import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.entities.Destinatario;
import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.StatusEntregaEnum;
import com.ibrplanner.logistica.exceptions.exceptionService.ExceptionService;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<EntregaDTO> listarTodos() {
        List<Entrega> entregas = entregaRepository.findAll();
        return toListEntregaDTO(entregas);
    }

    public EntregaDTO buscarPorId(Long id) {
        Optional<Entrega> entrega = entregaRepository.findById(id);
        return entrega.map(this::toEntregaDTO).orElse(null);
    }

    @Transactional
    public EntregaDTO salvar(EntregaDTO entregaDTO) {

        Cliente cliente = clienteRepository.findById(entregaDTO.getCliente().getId())
                .orElseThrow(() -> new ExceptionService("Cliente não encontrado."));

        Entrega entrega = toEntrega(entregaDTO, cliente);
        entrega.setStatus(StatusEntregaEnum.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        entrega = entregaRepository.save(entrega);
        return toEntregaDTO(entrega);
    }

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = clienteService.findById(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntregaEnum.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

    private Entrega toEntrega(EntregaDTO entregaDTO, Cliente cliente) {
        Entrega entrega = ObjectConverter.toModel(entregaDTO, Entrega.class);
        entrega.setCliente(ObjectConverter.toModel(cliente, Cliente.class));
        return entrega;
    }

    private Destinatario toDestinatario(DestinatarioDTO destinatarioDTO) {
        return ObjectConverter.toModel(destinatarioDTO, Destinatario.class);
    }

    private EntregaDTO toEntregaDTO(Entrega entrega) {
        return ObjectConverter.toModel(entrega, EntregaDTO.class);
    }

    private DestinatarioDTO toDestinatarioDTO(Destinatario destinatario) {
        return ObjectConverter.toModel(destinatario, DestinatarioDTO.class);
    }

    private List<EntregaDTO> toListEntregaDTO(List<Entrega> entregas) {
        return ObjectConverter.toListModel(entregas, EntregaDTO.class);
    }
}
