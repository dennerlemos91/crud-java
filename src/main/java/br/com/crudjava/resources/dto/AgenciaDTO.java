package br.com.crudjava.resources.dto;

import br.com.crudjava.model.Banco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgenciaDTO {

    private Long id;
    private Long idBanco;
    private String endereco;

}
