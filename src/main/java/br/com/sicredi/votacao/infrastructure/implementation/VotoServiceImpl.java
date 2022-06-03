package br.com.sicredi.votacao.infrastructure.implementation;

import br.com.sicredi.votacao.business.model.Pauta;
import br.com.sicredi.votacao.business.model.Voto;
import br.com.sicredi.votacao.business.service.VotoService;
import br.com.sicredi.votacao.infrastructure.repository.PautaRepository;
import br.com.sicredi.votacao.infrastructure.repository.VotoRepository;
import br.com.sicredi.votacao.business.dto.VotoDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class VotoServiceImpl implements VotoService {

    final String urlCpf = "https://user-info.herokuapp.com/users/";
    final String STATUS_APTO = "ABLE_TO_VOTE";

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final ModelMapper modelMapper;

    public VotoServiceImpl(PautaRepository pautaRepository, VotoRepository votoRepository,
                           ModelMapper modelMapper) {
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Voto create(VotoDTO votoDto) {
        log.debug("Inicio criar voto.");
        Assert.notNull(votoDto.getIdPauta(), "Pauta não informada.");
        Optional<Pauta> pautaOpt = pautaRepository.findById(votoDto.getIdPauta());
        Assert.isTrue(pautaOpt.isPresent(), "Pauta não encontrada.");
        Assert.notNull(pautaOpt.get().getDtVotacaoFim(), "Pauta não iniciada.");
        Assert.isTrue(LocalDateTime.now().isBefore(pautaOpt.get().getDtVotacaoFim()), "Pauta encerrada.");

        Optional<Voto> votoOpt = votoRepository.findByIdPautaAndNumCpf(votoDto.getIdPauta(), votoDto.getNumCpf());
        Assert.isTrue(!votoOpt.isPresent(), "Voto já registrado.");

        log.debug("Validacao externa cpf...");

        /*try {
            String strJson = new RestTemplate().getForObject(urlCpf + votoDto.getNumCpf(), String.class);
            StatusDTO status = new GsonBuilder().create().fromJson(strJson, StatusDTO.class);
            Assert.isTrue(STATUS_APTO.equals(status.getStatus()), "CPF impossibilitado de votar.");
        } catch (Exception e) {
            throw new IllegalArgumentException("CPF inválido ou impossibilitado de votar.");
        }*/

        Voto voto = modelMapper.map(votoDto, Voto.class);

        log.debug("Gerando voto idPauta:[" + voto.getIdPauta() + "] voto=[" + voto.getIndVotoSim()+"]");
        return votoRepository.save(voto);
    }

}
