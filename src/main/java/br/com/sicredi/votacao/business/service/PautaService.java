package br.com.sicredi.votacao.business.service;

import br.com.sicredi.votacao.business.dto.VotacaoDTO;
import br.com.sicredi.votacao.business.model.Pauta;

import java.math.BigInteger;
import java.util.List;

public interface PautaService {
    Pauta create(String desPauta);
    Pauta findById(BigInteger id);
    List<Pauta> findAll();
    void abrirPauta(BigInteger idPauta, Integer qtdMinutos);
    VotacaoDTO fecharPauta(BigInteger idPauta);
}
