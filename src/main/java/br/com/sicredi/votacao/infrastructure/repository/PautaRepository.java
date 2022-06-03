package br.com.sicredi.votacao.infrastructure.repository;

import br.com.sicredi.votacao.business.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PautaRepository extends JpaRepository<Pauta, BigInteger> {

    Pauta findByDesPautaIgnoreCase(String desPauta);

}
