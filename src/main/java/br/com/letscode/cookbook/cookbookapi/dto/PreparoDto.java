package br.com.letscode.cookbook.cookbookapi.dto;

import br.com.letscode.cookbook.cookbookapi.model.Preparo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PreparoDto {
    private Integer passo;
    private String descricao;

    public PreparoDto(Preparo preparo) {
        this.passo = preparo.getPasso();
        this.descricao = preparo.getDescricao();
    }
}
