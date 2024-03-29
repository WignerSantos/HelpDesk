package com.wigner.helpdesk.Services;

import com.wigner.helpdesk.Services.exceptions.DataIntegrityViolationException;
import com.wigner.helpdesk.Services.exceptions.ObjectNotFoundException;
import com.wigner.helpdesk.domain.Pessoa;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.dtos.TecnicoDto;
import com.wigner.helpdesk.repositories.PessoaRepository;
import com.wigner.helpdesk.repositories.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto tecnicoDto) {
        tecnicoDto.setId(null);

        validaPorCpfEEmail(tecnicoDto);
        tecnicoDto.setSenha(encoder.encode(tecnicoDto.getSenha()));

        Tecnico newTecnico = new Tecnico(tecnicoDto);
        return tecnicoRepository.save(newTecnico);
    }

    public Tecnico update(Integer id, @Valid TecnicoDto tecnicoDto) {
        tecnicoDto.setId(id);
        Tecnico oldTecnico = findById(id);

        if(!tecnicoDto.getSenha().equals(oldTecnico.getSenha())) {
            tecnicoDto.setSenha(encoder.encode(tecnicoDto.getSenha()));
        };

        validaPorCpfEEmail(tecnicoDto);

        oldTecnico = new Tecnico(tecnicoDto);
        return tecnicoRepository.save(oldTecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if(tecnico.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    public void validaPorCpfEEmail(TecnicoDto tecnicoDto) {
        Optional<Pessoa> obj = pessoaRepository.readByCpf(tecnicoDto.getCpf());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), tecnicoDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.readByEmail(tecnicoDto.getEmail());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), tecnicoDto.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

}
