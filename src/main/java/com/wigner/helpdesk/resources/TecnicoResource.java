package com.wigner.helpdesk.resources;

import com.wigner.helpdesk.Services.TecnicoService;
import com.wigner.helpdesk.domain.Tecnico;
import com.wigner.helpdesk.domain.dtos.TecnicoDto;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}