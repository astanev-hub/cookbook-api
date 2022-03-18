package br.com.letscode.cookbook.cookbookapi.repository;

import br.com.letscode.cookbook.cookbookapi.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, String> {
}