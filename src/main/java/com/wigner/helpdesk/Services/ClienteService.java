package com.wigner.helpdesk.Services;

import com.wigner.helpdesk.Services.exceptions.DataIntegrityViolationException;
import com.wigner.helpdesk.Services.exceptions.ObjectNotFoundException;
import com.wigner.helpdesk.domain.Cliente;
import com.wigner.helpdesk.domain.Pessoa;
import com.wigner.helpdesk.domain.dtos.ClienteDto;
import com.wigner.helpdesk.repositories.ClienteRepository;
import com.wigner.helpdesk.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDto clienteDto) {
        clienteDto.setId(null);

        validaPorCpfEEmail(clienteDto);

        Cliente newCliente = new Cliente(clienteDto);
        return clienteRepository.save(newCliente);
    }

    public Cliente update(Integer id, @Valid ClienteDto clienteDto) {
        clienteDto.setId(id);

        Cliente oldCliente = findById(id);
        validaPorCpfEEmail(clienteDto);

        oldCliente = new Cliente(clienteDto);
        return clienteRepository.save(oldCliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if(cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    public void validaPorCpfEEmail(ClienteDto clienteDto) {
        Optional<Pessoa> obj = pessoaRepository.readByCpf(clienteDto.getCpf());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), clienteDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.readByEmail(clienteDto.getEmail());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), clienteDto.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

}
