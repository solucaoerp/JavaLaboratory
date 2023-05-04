package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.common.ObjectConverter;
import com.ibrplanner.logistica.dtos.OcorrenciaDTO;
import com.ibrplanner.logistica.dtos.OcorrenciaInput;
import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.entities.Ocorrencia;
import com.ibrplanner.logistica.services.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ObjectConverter.toModel(ocorrenciaRegistrada, OcorrenciaDTO.class);
    }

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        Entrega entrega = ocorrenciaService.buscarPorId(entregaId);

        return ObjectConverter.toListModel(entrega.getOcorrencias(), OcorrenciaDTO.class);
    }

}
