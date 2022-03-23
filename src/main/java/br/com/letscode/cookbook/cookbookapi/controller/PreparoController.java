package br.com.letscode.cookbook.cookbookapi.controller;

import br.com.letscode.cookbook.cookbookapi.dto.PreparoDto;
import br.com.letscode.cookbook.cookbookapi.model.Preparo;
import br.com.letscode.cookbook.cookbookapi.repository.PreparoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "preparo")
public class PreparoController {
    private PreparoRepository repository;

    public PreparoController(PreparoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PreparoDto> getAll() {
        return repository.findAll().stream()
                .map(PreparoDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public PreparoDto findById(@PathVariable Integer id) {
        return new PreparoDto(repository.getById(id));
    }
}
