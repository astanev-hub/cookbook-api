package br.com.letscode.cookbook.cookbookapi.repository;

import br.com.letscode.cookbook.cookbookapi.model.ReceitaIngrediente;
import br.com.letscode.cookbook.cookbookapi.model.ReceitaIngredientePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, ReceitaIngredientePK> {
}