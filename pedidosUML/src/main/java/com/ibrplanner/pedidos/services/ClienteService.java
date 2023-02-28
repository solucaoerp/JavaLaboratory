package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Cliente;
import com.ibrplanner.pedidos.dtos.ClienteDTO;
import com.ibrplanner.pedidos.helpers.DTOUtils;
import com.ibrplanner.pedidos.repositories.ClienteRepository;
import com.ibrplanner.pedidos.services.exeptions.DataIntegrityException;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> list = repo.findAll();
        return DTOUtils.toDTOList(list, ClienteDTO.class);
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null); /* garante que seja um novo registro, do contrário é um update */
        return repo.save(obj);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro ao deletar o Cliente Id: " + id);
        }
    }

    private ClienteDTO toClienteDTO(Cliente Cliente) {
        return DTOUtils.convertToDTO(Cliente, ClienteDTO.class);
    }

    public Page<ClienteDTO> findPage(Integer numPage, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(numPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Cliente> page = repo.findAll(pageRequest);
        return DTOUtils.toDTOPage(page, ClienteDTO.class);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
