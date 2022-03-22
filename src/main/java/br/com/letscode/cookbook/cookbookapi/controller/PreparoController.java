package br.com.letscode.cookbook.cookbookapi.controller;

import br.com.letscode.cookbook.cookbookapi.model.Preparo;
import br.com.letscode.cookbook.cookbookapi.repository.PreparoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "preparo")
public class PreparoController {
    private PreparoRepository repository;

    public PreparoController(PreparoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Preparo> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Preparo findById(@PathVariable Integer id) {
        return repository.getById(id);
    }
}
