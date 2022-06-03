package br.com.sicredi.votacao.business.controller;

import br.com.sicredi.votacao.business.dto.VotoDTO;
import br.com.sicredi.votacao.business.model.Voto;
import br.com.sicredi.votacao.business.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService){
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody VotoDTO votoDto) throws URISyntaxException {
        Voto voto = votoService.create(votoDto);
        return ResponseEntity.created(new URI(String.format("%d", voto.getId())))
                .build();
    }

}
