package br.com.letscode.cookbook.cookbookapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "receita", schema = "cookbook")
@Getter
@Setter
public class Receita {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "nome", length = 120)
    private String nome;

    @Column(name = "categoria", length = 30)
    private String categoria;

    @Column(name = "tempo_preparo")
    private Integer tempoPreparo;

    @Column(name = "rendimento_tipo", length = 30)
    private String rendimentoTipo;

    @Column(name = "rendimento_quantidade")
    private Integer rendimentoQuantidade;

    @OneToMany(mappedBy = "receita", fetch = FetchType.LAZY)
    private Set<Preparo> preparos;

    @OneToMany(mappedBy = "receita", fetch = FetchType.LAZY)
    private Set<ReceitaIngrediente> ingredientes;
}
