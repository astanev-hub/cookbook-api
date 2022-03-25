package br.com.letscode.cookbook.cookbookapi.controller;

import br.com.letscode.cookbook.cookbookapi.model.Ingrediente;
import br.com.letscode.cookbook.cookbookapi.repository.IngredienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

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
        return repository.findById(nome).orElse(null);
    }

    @GetMapping(path = "/filter")
    public Page<Ingrediente> filter(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Ingrediente add(Ingrediente ingrediente) {
        if (repository.findById(ingrediente.getNome()).isPresent()) {
            throw new RestClientException("Ingrediente já existe!!!");
        }
        return repository.save(ingrediente);
    }

    @PutMapping
    public Ingrediente update(Ingrediente ingrediente) {
        Ingrediente entity = repository.findById(ingrediente.getNome()).orElse(null);
        if (entity == null)
            throw new RestClientException("Ingrediente não existente!!!");

        entity.setDescricao(ingrediente.getDescricao());
        return repository.save(entity);
    }

    @DeleteMapping("/{nome}")
    public Ingrediente delete(@PathVariable String nome) {
//        repository.deleteById(nome);

        Ingrediente entity = repository.findById(nome).orElse(null);
        if (entity == null)
            throw new RestClientException("Ingrediente não existente!!!");

        repository.delete(entity);
        return entity;
    }
}
