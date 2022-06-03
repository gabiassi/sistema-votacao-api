package br.com.sicredi.votacao.business.service;

import br.com.sicredi.votacao.business.dto.VotoDTO;
import br.com.sicredi.votacao.business.model.Voto;

public interface VotoService {
    Voto create(VotoDTO votoDto);
}
