package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Cidade;
import com.ibrplanner.pedidos.domain.Cliente;
import com.ibrplanner.pedidos.domain.Endereco;
import com.ibrplanner.pedidos.dtos.ClienteDTO;
import com.ibrplanner.pedidos.dtos.ClienteNewDTO;
import com.ibrplanner.pedidos.enums.TipoCliente;
import com.ibrplanner.pedidos.helpers.DTOUtils;
import com.ibrplanner.pedidos.repositories.CidadeRepository;
import com.ibrplanner.pedidos.repositories.ClienteRepository;
import com.ibrplanner.pedidos.repositories.EnderecoRepository;
import com.ibrplanner.pedidos.services.exeptions.DataIntegrityException;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private CidadeRepository repoCidade;
    @Autowired
    private EnderecoRepository repoEndereco;

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> list = repo.findAll();
        return DTOUtils.toDTOList(list, ClienteDTO.class);
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null); /* id -> auto-incremento */
        obj = repo.save(obj);
        repoEndereco.saveAll(obj.getEnderecos());
        return obj;
    }
    @Transactional
    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o Cliente [ " + id + " ] porque há pedidos relacionados.");
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
    @Transactional
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
}
