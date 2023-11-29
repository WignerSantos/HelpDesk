package com.wigner.helpdesk.resources;

import com.wigner.helpdesk.Services.ChamadoService;
import com.wigner.helpdesk.domain.Chamado;
import com.wigner.helpdesk.domain.dtos.ChamadoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResources {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll() {
        List<Chamado> chamados = chamadoService.findAll();
        List<ChamadoDto> chamadosDto = chamados.stream().map(ChamadoDto::new).toList();
        return ResponseEntity.ok().body(chamadosDto);
    }

    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto objDto) {
        Chamado chamado = chamadoService.create(objDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(objDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
