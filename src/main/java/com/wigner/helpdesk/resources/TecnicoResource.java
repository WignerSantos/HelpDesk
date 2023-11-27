package com.wigner.helpdesk.resources;

import com.wigner.helpdesk.Services.TecnicoService;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.dtos.TecnicoDto;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);

        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDto> listDto = list.stream().map(TecnicoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto tecnico) {
        Tecnico newObj = tecnicoService.create(tecnico);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDto tecnicoDto) {
        Tecnico oldTecnico = tecnicoService.update(id, tecnicoDto);
        return ResponseEntity.ok().body(new TecnicoDto(oldTecnico));
    }

}
