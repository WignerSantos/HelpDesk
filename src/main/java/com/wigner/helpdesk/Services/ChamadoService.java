package com.wigner.helpdesk.Services;

import com.wigner.helpdesk.Services.exceptions.ObjectNotFoundException;
import com.wigner.helpdesk.domain.Chamado;
import com.wigner.helpdesk.repositories.ChamadoRepository;
import com.wigner.helpdesk.resources.ChamadoResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

}
