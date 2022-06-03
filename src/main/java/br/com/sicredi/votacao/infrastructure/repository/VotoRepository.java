package br.com.sicredi.votacao.infrastructure.repository;

import br.com.sicredi.votacao.business.model.Voto;
import br.com.sicredi.votacao.business.dto.VotacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, BigInteger> {

    /*static final String QUERY_SUM =
            "SELECT new br.com.sicredi.votacao.business.dto.VotacaoDTO(p.id, p.desPauta," +
                    " (SELECT count(1) FROM voto v where v.idPauta = p.id and v.indVotoSim = 1)," +
                    " (SELECT count(1) FROM voto v where v.idPauta = p.id and v.indVotoSim != 1))" +
                    " FROM pauta p" +
                    " WHERE p.id = ?1";*/

    @Query("SELECT e FROM voto e WHERE e.idPauta = ?1 AND e.numCpf = ?2")
    Optional<Voto> findVoto(BigInteger idPauta, Long cpf);

    Optional<Voto> findByIdPautaAndNumCpf(BigInteger idPauta, Long cpf);

    void deleteByIdPauta(BigInteger id);

    @Query("SELECT new br.com.sicredi.votacao.business.dto.VotacaoDTO(p.id, p.desPauta," +
            " (SELECT count(1) FROM voto v where v.idPauta = p.id and v.indVotoSim = 1)," +
            " (SELECT count(1) FROM voto v where v.idPauta = p.id and v.indVotoSim != 1))" +
            " FROM pauta p" +
            " WHERE p.id = ?1")
    VotacaoDTO sumVotes(BigInteger id);
}
