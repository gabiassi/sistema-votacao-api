package br.com.sicredi.votacao.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@NoArgsConstructor
@Data
@Entity(name = "voto")
@Table(name = "voto")
@SequenceGenerator(name = "votoSeq", sequenceName = "voto_sequence", allocationSize = 1)
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "votoSeq")
    private BigInteger id;

    @Column(name = "id_pauta")
    private BigInteger idPauta;

    @Column(name = "num_cpf")
    private Long numCpf;

    @Column(name = "ind_voto_sim")
    private Integer indVotoSim;

}
