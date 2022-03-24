package br.com.letscode.cookbook.cookbookapi.controller;

import br.com.letscode.cookbook.cookbookapi.dto.ReceitaDto;
import br.com.letscode.cookbook.cookbookapi.dto.ReceitaIngredienteDto;
import br.com.letscode.cookbook.cookbookapi.model.Preparo;
import br.com.letscode.cookbook.cookbookapi.model.Receita;
import br.com.letscode.cookbook.cookbookapi.model.ReceitaIngrediente;
import br.com.letscode.cookbook.cookbookapi.model.ReceitaIngredientePK;
import br.com.letscode.cookbook.cookbookapi.repository.IngredienteRepository;
import br.com.letscode.cookbook.cookbookapi.repository.PreparoRepository;
import br.com.letscode.cookbook.cookbookapi.repository.ReceitaIngredienteRepository;
import br.com.letscode.cookbook.cookbookapi.repository.ReceitaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "receita")
public class ReceitaController {
    private ReceitaRepository repository;
    private PreparoRepository preparoRepository;
    private ReceitaIngredienteRepository receitaIngredienteRepository;
    private IngredienteRepository ingredienteRepository;

    public ReceitaController(ReceitaRepository repository, PreparoRepository preparoRepository, ReceitaIngredienteRepository receitaIngredienteRepository, IngredienteRepository ingredienteRepository) {
        this.repository = repository;
        this.preparoRepository = preparoRepository;
        this.receitaIngredienteRepository = receitaIngredienteRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @GetMapping
    public List<ReceitaDto> getAll() {
        return repository.findAll().stream().map(ReceitaDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "filter")
    public Page<ReceitaDto> filter(Pageable pageable) {
        return repository.findAll(pageable).map(ReceitaDto::new);
    }

    @GetMapping(path = "/{nome}")
    public ReceitaDto findById(@PathVariable String nome) {
        return new ReceitaDto(repository.getById(nome));
    }

    @Transactional
    @DeleteMapping(path = "/{nome}")
    public void delete(@PathVariable String nome) {
        Receita receita = repository.findById(nome).orElse(null);
        if (receita == null) throw new RestClientException("Receita not found for delete");
        receita.getPreparos().forEach(preparoRepository::delete);
        receita.getIngredientes().forEach(receitaIngredienteRepository::delete);
        repository.delete(receita);
    }

    @Transactional
    @PostMapping
    public ReceitaDto addNew(@RequestBody ReceitaDto receita) {
        if (repository.getById(receita.getNome()) != null) throw new RestClientException("Receita duplicated");
        Receita entity = new Receita();
        entity.setNome(receita.getNome());
        entity.setCategoria(receita.getCategoria());
        entity.setTempoPreparo(receita.getTempoPreparo());
        entity.setRendimentoTipo(receita.getRendimentoTipo());
        entity.setRendimentoQuantidade(receita.getRendimentoQuantidade());
        repository.save(entity);
        savePreparos(receita.getPreparos(), entity);
        List<ReceitaIngredienteDto> ingredientes = receita.getIngredientes();
        ingredientes.forEach(dto -> saveReceitaIngrediente(entity, dto));
        return new ReceitaDto(repository.findById(receita.getNome()).get());
    }

    @Transactional
    @PutMapping
    public ReceitaDto update(@RequestBody ReceitaDto receita) {
        Receita entity = repository.findById(receita.getNome()).orElse(null);
        if (entity == null) throw new RestClientException("Receita not found for update");
        entity.setCategoria(receita.getCategoria());
        entity.setTempoPreparo(receita.getTempoPreparo());
        entity.setRendimentoTipo(receita.getRendimentoTipo());
        entity.setRendimentoQuantidade(receita.getRendimentoQuantidade());
        repository.save(entity);
        entity.getPreparos().stream()
                .filter(preparo -> !receita.getPreparos().containsKey(preparo.getPasso()))
                .forEach(preparoRepository::delete);
        savePreparos(receita.getPreparos(), entity);
        entity.getIngredientes().stream()
                .filter(receitaIngrediente -> receita.getIngredientes().stream().noneMatch(dto -> dto.getIngredienteNome().equalsIgnoreCase(receitaIngrediente.getId().getIngredienteNome())))
                .forEach(receitaIngredienteRepository::delete);
        receita.getIngredientes().forEach(dto -> saveReceitaIngrediente(entity, dto));
        return new ReceitaDto(repository.findById(receita.getNome()).get());
    }

    private void saveReceitaIngrediente(Receita entity, ReceitaIngredienteDto dto) {
        ReceitaIngrediente receitaIngrediente = entity.getIngredientes().stream()
                .filter(item -> item.getId().getIngredienteNome().equalsIgnoreCase(dto.getIngredienteNome()))
                .findFirst().orElse(new ReceitaIngrediente());
        receitaIngrediente.setId(new ReceitaIngredientePK(entity.getNome(), dto.getIngredienteNome()));
        receitaIngrediente.setQuantidade(dto.getQuantidade());
        receitaIngrediente.setTipoMedida(dto.getTipoMedida());
        receitaIngredienteRepository.save(receitaIngrediente);
    }

    private void savePreparos(Map<Integer, String> preparos, Receita entity) {
        preparos.forEach((passo, descricao) -> {
            Preparo preparo = entity.getPreparos().stream()
                    .filter(item -> item.getPasso().equals(passo))
                    .findFirst().orElse(new Preparo());
            preparo.setReceita(entity);
            preparo.setPasso(passo);
            preparo.setDescricao(descricao);
            preparoRepository.save(preparo);
        });
    }
}
