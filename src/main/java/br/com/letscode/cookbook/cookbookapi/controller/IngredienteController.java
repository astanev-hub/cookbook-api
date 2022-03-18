package br.com.letscode.cookbook.cookbookapi.controller;

import br.com.letscode.cookbook.cookbookapi.model.Ingrediente;
import br.com.letscode.cookbook.cookbookapi.repository.IngredienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ingrediente")
public class IngredienteController {
    private IngredienteRepository repository;

    public IngredienteController(IngredienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ingrediente> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{nome}")
    public Ingrediente findByName(@PathVariable String nome) {
        return repository.getById(nome);
    }
}
