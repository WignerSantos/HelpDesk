package com.wigner.helpdesk.Services;

import com.wigner.helpdesk.Services.exceptions.ObjectNotFoundException;
import com.wigner.helpdesk.domain.Chamado;
import com.wigner.helpdesk.domain.Cliente;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.dtos.ChamadoDto;
import com.wigner.helpdesk.domain.enums.Prioridade;
import com.wigner.helpdesk.domain.enums.Status;
import com.wigner.helpdesk.repositories.ChamadoRepository;
import com.wigner.helpdesk.resources.ChamadoResources;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid ChamadoDto chamadoDto) {
        return chamadoRepository.save(newChamado(chamadoDto));
    }

    private Chamado newChamado(ChamadoDto chamadoDto) {
        Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDto.getCliente());

        Chamado chamado = new Chamado();
        if(chamadoDto.getId() != null) {
            chamado.setId(chamadoDto.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
        chamado.setTitulo(chamadoDto.getTitulo());
        chamado.setObservacoes(chamadoDto.getObservacoes());
        return chamado;
    }

}
