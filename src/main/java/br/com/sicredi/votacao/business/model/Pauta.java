package br.com.sicredi.votacao.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity(name = "pauta")
@Table(name = "pauta")
@SequenceGenerator(name = "pautaSeq", sequenceName = "pauta_sequence", allocationSize = 1)
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pautaSeq")
    private BigInteger id;

    @Column(name = "des_pauta", nullable = false, length = 50)
    private String desPauta;

    @Column(name = "dt_votacao_fim")
    private LocalDateTime dtVotacaoFim;

}
