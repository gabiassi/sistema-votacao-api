package br.com.sicredi.votacao.infrastructure.implementation;

import br.com.sicredi.votacao.business.model.Pauta;
import br.com.sicredi.votacao.business.dto.VotacaoDTO;
import br.com.sicredi.votacao.business.service.PautaService;
import br.com.sicredi.votacao.infrastructure.repository.PautaRepository;
import br.com.sicredi.votacao.infrastructure.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    public PautaServiceImpl(PautaRepository pautaRepository, VotoRepository votoRepository) {
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
    }

    @Override
    public Pauta create(String desPauta) {
        Pauta pauta = pautaRepository.findByDesPautaIgnoreCase(desPauta);
        Assert.isNull(pauta, "Pauta já cadastrada.");
        pauta = new Pauta();
        pauta.setDesPauta(desPauta);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta findById(BigInteger id) {
        Optional<Pauta> pautaOpt = pautaRepository.findById(id);
        Assert.isTrue(pautaOpt.isPresent(), "Pauta não encontrada.");
        return pautaOpt.get();
    }

    @Override
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    @Override
    @Transactional
    public void abrirPauta(BigInteger idPauta, Integer qtdMinutos) {
        log.debug("Inicio abertura de votacao pauta:" + idPauta);

        Optional<Pauta> pautaOpt = pautaRepository.findById(idPauta);
        Assert.isTrue(pautaOpt.isPresent(), "Pauta não encontrada.");

        Pauta pauta = pautaOpt.get();

        pauta.setDtVotacaoFim(LocalDateTime.now().plusMinutes(qtdMinutos));
        pautaRepository.save(pauta);

        votoRepository.deleteByIdPauta(pauta.getId());

        log.debug("Fim abertura de votacao pauta:" + idPauta);
    }

    @Override
    public VotacaoDTO fecharPauta(BigInteger idPauta) {
        log.debug("Inicio fechamento pauta:" + idPauta);
        Optional<Pauta> pautaOptional = pautaRepository.findById(idPauta);
        Assert.isTrue(pautaOptional.isPresent(), "Pauta não encontrada.");
        Pauta pauta = pautaOptional.get();

        pauta.setDtVotacaoFim(LocalDateTime.now());
        pautaRepository.save(pauta);

        VotacaoDTO dto = votoRepository.sumVotes(pauta.getId());
        log.debug("Fim fechamento pauta id=" + dto.getIdPauta() + " - S=" + dto.getQtdSim() + ", N=" + dto.getQtdNao());
        return dto;
    }

}
