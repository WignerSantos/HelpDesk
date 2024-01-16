package com.wigner.helpdesk.Services;

import com.wigner.helpdesk.domain.Chamado;
import com.wigner.helpdesk.domain.Cliente;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.enums.Perfil;
import com.wigner.helpdesk.domain.enums.Prioridade;
import com.wigner.helpdesk.domain.enums.Status;
import com.wigner.helpdesk.repositories.ChamadoRepository;
import com.wigner.helpdesk.repositories.ClienteRepository;
import com.wigner.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "George Henrique", "655.685.745-96", "georgehenrique233@gmail.com", passwordEncoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Leozinho Vitorio", "215.363.114-99", "leozinhovito@gmail.com", passwordEncoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "731.839.816-45", "linustorvalds@hotmail.com", passwordEncoder.encode("123"));
        Chamado ch1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);

        Cliente cli2 = new Cliente(null, "Steve Jobs", "969.797.549-39", "stevejobs@protonmail.com", passwordEncoder.encode("123"));
        Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 02", "Segundo Chamado", tec2, cli2);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        chamadoRepository.saveAll(Arrays.asList(ch1, ch2));
    }

}
