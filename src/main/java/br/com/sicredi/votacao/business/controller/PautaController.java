package br.com.sicredi.votacao.business.controller;

import br.com.sicredi.votacao.business.dto.PautaDTO;
import br.com.sicredi.votacao.business.dto.VotacaoDTO;
import br.com.sicredi.votacao.business.model.Pauta;
import br.com.sicredi.votacao.business.service.PautaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {

    private final PautaService pautaService;
    private final ModelMapper modelMapper;

    public PautaController(PautaService pautaService, ModelMapper modelMapper) {
        this.pautaService = pautaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{desPauta}")
    public ResponseEntity create(@PathVariable("desPauta") String desPauta) throws URISyntaxException {
        Pauta pauta = pautaService.create(desPauta);
        return ResponseEntity.created(new URI(String.format("%d", pauta.getId())))
                .build();
    }

    @GetMapping
    public List<PautaDTO> getAllPautas() {
        return pautaService.findAll().stream()
                .map(pauta -> modelMapper.map(pauta, PautaDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") BigInteger id) {
        try {
            Pauta pauta = pautaService.findById(id);
            return ResponseEntity.ok(pauta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/abrir/{id}")
    public ResponseEntity abrirPauta(@PathVariable("id") BigInteger idPauta,
                               @RequestParam(defaultValue = "1", required = false)
                               Integer qtdMinutos) {
        pautaService.abrirPauta(idPauta, qtdMinutos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/totais/{id}")
    public ResponseEntity fecharPauta(@PathVariable("id") BigInteger idPauta) {
        VotacaoDTO votacaoDto = pautaService.fecharPauta(idPauta);
        return ResponseEntity.ok(votacaoDto);
    }

}
