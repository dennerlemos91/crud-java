package br.com.crudjava.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Agencia implements Serializable {

    private static final long serialVersionUID = 215827378840061967L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Banco banco;
    private String endereco;

    @OneToMany(mappedBy = "agencia", fetch = FetchType.LAZY)
    List<Usuario> usuarios = new ArrayList<>();
}
