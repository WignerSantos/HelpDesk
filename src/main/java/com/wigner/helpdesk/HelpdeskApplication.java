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
@EnableJpaAuditing
public class HelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
