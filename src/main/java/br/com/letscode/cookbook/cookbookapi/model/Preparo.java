package br.com.letscode.cookbook.cookbookapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preparo", schema = "cookbook")
@Getter
@Setter
public class Preparo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "passo")
    private Integer passo;

    @Column(name = "descricao", length = 250)
    private String descricao;

}
