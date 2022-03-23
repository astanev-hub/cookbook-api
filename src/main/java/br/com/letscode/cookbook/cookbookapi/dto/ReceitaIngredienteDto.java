package br.com.letscode.cookbook.cookbookapi.dto;

import br.com.letscode.cookbook.cookbookapi.enums.TipoMedida;
import br.com.letscode.cookbook.cookbookapi.model.ReceitaIngrediente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitaIngredienteDto {
    private String ingredienteNome;
    private Integer quantidade;
    private TipoMedida tipoMedida;

    public ReceitaIngredienteDto(ReceitaIngrediente receitaIngrediente) {
        this.ingredienteNome = receitaIngrediente.getIngrediente().getNome();
        this.quantidade = receitaIngrediente.getQuantidade();
        this.tipoMedida = receitaIngrediente.getTipoMedida();
    }
}
