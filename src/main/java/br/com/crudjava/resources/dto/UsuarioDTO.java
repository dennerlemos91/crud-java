package br.com.crudjava.resources.dto;

import br.com.crudjava.model.Agencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private Integer cep;
    private Long idAgencia;
    private Integer conta;
}
