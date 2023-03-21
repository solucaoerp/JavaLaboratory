package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.controllers.dtos.DestinatarioDTO;
import com.ibrplanner.logistica.controllers.dtos.EntregaDTO;
import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.entities.Destinatario;
import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.StatusEntrega;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import com.ibrplanner.logistica.services.converterUtils.ConverterUtils;
import com.ibrplanner.logistica.services.exceptions.ExceptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ModelMapper modelMapper;

    public List<EntregaDTO> listarTodos() {
        List<Entrega> entregas = entregaRepository.findAll();
        return toListEntregaDTO(entregas);
    }

    public EntregaDTO buscarPorId(Long id) {
        Optional<Entrega> entrega = entregaRepository.findById(id);
        return entrega.map(this::toEntregaDTO).orElse(null);
    }

    public EntregaDTO salvar(EntregaDTO entregaDTO) {

        Cliente cliente = clienteRepository.findById(entregaDTO.getCliente().getId())
                .orElseThrow(() -> new ExceptionService("Cliente não encontrado."));

        Entrega entrega = toEntrega(entregaDTO, cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        entrega = entregaRepository.save(entrega);
        return toEntregaDTO(entrega);
    }

    private Entrega toEntrega(EntregaDTO entregaDTO, Cliente cliente) {
        Entrega entrega = ConverterUtils.toModel(entregaDTO, Entrega.class);
        entrega.setCliente(ConverterUtils.toModel(cliente, Cliente.class));
        return entrega;
    }

    private Destinatario toDestinatario(DestinatarioDTO destinatarioDTO) {
        return ConverterUtils.toModel(destinatarioDTO, Destinatario.class);
    }

    private EntregaDTO toEntregaDTO(Entrega entrega) {
        return ConverterUtils.toModel(entrega, EntregaDTO.class);
    }

    private DestinatarioDTO toDestinatarioDTO(Destinatario destinatario) {
        return ConverterUtils.toModel(destinatario, DestinatarioDTO.class);
    }

    private List<EntregaDTO> toListEntregaDTO(List<Entrega> entregas) {
        return ConverterUtils.toListModel(entregas, EntregaDTO.class, modelMapper);
    }
}
