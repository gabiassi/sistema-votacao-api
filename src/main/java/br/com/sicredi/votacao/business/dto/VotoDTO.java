package br.com.sicredi.votacao.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotoDTO {

    private BigInteger idPauta;
    private Long numCpf;
    private Integer indVotoSim;

}
