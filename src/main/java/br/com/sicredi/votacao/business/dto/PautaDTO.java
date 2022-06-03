package br.com.sicredi.votacao.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PautaDTO {
    private BigInteger id;
    private String desPauta;
    private LocalDateTime dtVotacaoFim;
}
