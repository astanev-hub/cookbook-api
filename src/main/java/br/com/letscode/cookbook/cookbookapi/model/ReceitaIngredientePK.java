package br.com.letscode.cookbook.cookbookapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ReceitaIngredientePK implements Serializable {
    @Column(name = "receita_nome", length = 120)
    private String receitaNome;
    @Column(name = "ingrediente_nome", length = 120)
    private String ingredienteNome;
}
