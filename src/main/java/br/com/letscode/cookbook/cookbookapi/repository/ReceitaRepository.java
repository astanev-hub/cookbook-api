package br.com.letscode.cookbook.cookbookapi.repository;

import br.com.letscode.cookbook.cookbookapi.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, String> {
}