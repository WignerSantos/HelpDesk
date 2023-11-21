package com.wigner.helpdesk;

import com.wigner.helpdesk.domain.Chamado;
import com.wigner.helpdesk.domain.Cliente;
import com.wigner.helpdesk.domain.Pessoa;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.enums.Perfil;
import com.wigner.helpdesk.domain.enums.Prioridade;
import com.wigner.helpdesk.domain.enums.Status;
import com.wigner.helpdesk.repositories.ChamadoRepository;
import com.wigner.helpdesk.repositories.ClienteRepository;
import com.wigner.helpdesk.repositories.PessoaRepository;
import com.wigner.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication
@EntityScan("com.wigner.helpdesk.domain")
@EnableJpaAuditing
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;


	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "63653230268", "valdir@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@gmail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", cli1, tec1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}
}
