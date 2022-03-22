package br.com.letscode.cookbook.cookbookapi.repository;

import br.com.letscode.cookbook.cookbookapi.model.Preparo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreparoRepository extends JpaRepository<Preparo, Integer> {
}