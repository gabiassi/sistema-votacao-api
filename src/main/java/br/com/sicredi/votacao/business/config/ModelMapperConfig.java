package br.com.sicredi.votacao.business.config;

import br.com.sicredi.votacao.business.dto.PautaDTO;
import br.com.sicredi.votacao.business.dto.VotoDTO;
import br.com.sicredi.votacao.business.model.Pauta;
import br.com.sicredi.votacao.business.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    /*modelMapper
        .createTypeMap(Carro.class, CarroDTO.class)
        .addMapping(c -> c.getModelo().getNome(), CarroDTO::setModelo)
        .addMapping(c -> c.getModelo().getMarca().getNome(), CarroDTO::setMarca);*/


        modelMapper
                .createTypeMap(PautaDTO.class, Pauta.class);
    modelMapper
            .createTypeMap(VotoDTO.class, Voto.class);


    return modelMapper;
  }
}
