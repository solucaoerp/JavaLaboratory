package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.controllers.dtos.DestinatarioDTO;
import com.ibrplanner.logistica.controllers.dtos.EntregaDTO;
import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.entities.Destinatario;
import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.StatusEntrega;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import com.ibrplanner.logistica.services.exceptions.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Entrega entrega = new Entrega();
        entrega.setId(entregaDTO.getId());
        entrega.setCliente(cliente);
        entrega.setDestinatario(toDestinatario(entregaDTO.getDestinatario()));
        entrega.setTaxaEntrega(entregaDTO.getTaxaEntrega());
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entrega;
    }

    private Destinatario toDestinatario(DestinatarioDTO destinatarioDTO) {
        Destinatario destinatario = new Destinatario();
        destinatario.setNome(destinatarioDTO.getNome());
        destinatario.setLogradouro(destinatarioDTO.getLogradouro());
        destinatario.setNumero(destinatarioDTO.getNumero());
        destinatario.setComplemento(destinatarioDTO.getComplemento());
        destinatario.setBairro(destinatarioDTO.getBairro());
        return destinatario;
    }

    private EntregaDTO toEntregaDTO(Entrega entrega) {
        EntregaDTO entregaDTO = new EntregaDTO();
        entregaDTO.setId(entrega.getId());
        entregaDTO.setCliente(entrega.getCliente());
        entregaDTO.setDestinatario(toDestinatarioDTO(entrega.getDestinatario()));
        entregaDTO.setTaxaEntrega(entrega.getTaxaEntrega());
        entregaDTO.setStatus(entrega.getStatus());
        entregaDTO.setDataPedido(entrega.getDataPedido());
        entregaDTO.setDataFinalizacao(entrega.getDataFinalizacao());
        return entregaDTO;
    }

    private DestinatarioDTO toDestinatarioDTO(Destinatario destinatario) {
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setNome(destinatario.getNome());
        destinatarioDTO.setLogradouro(destinatario.getLogradouro());
        destinatarioDTO.setNumero(destinatario.getNumero());
        destinatarioDTO.setComplemento(destinatario.getComplemento());
        destinatarioDTO.setBairro(destinatario.getBairro());
        return destinatarioDTO;
    }

    private List<EntregaDTO> toListEntregaDTO(List<Entrega> entregas) {
        return entregas.stream().map(this::toEntregaDTO).collect(Collectors.toList());
    }
}
