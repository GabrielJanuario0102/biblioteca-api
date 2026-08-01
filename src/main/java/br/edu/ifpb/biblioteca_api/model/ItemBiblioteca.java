package br.edu.ifpb.biblioteca_api.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.edu.ifpb.biblioteca_api.model.enums.ClassificacaoIndicativa;
import br.edu.ifpb.biblioteca_api.model.enums.TipoItem;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Document(collection = "itens") 
public class ItemBiblioteca {

    @Id
    private String id;

    @NotBlank(message = "Campo de Titulo não pode ser vazio.")
    private String titulo;

    private String isbn;

    private String editora;

    @NotBlank(message = "Campo de Autor não pode ser vazio.")
    private String autor;

    @NotNull(message = "Algum tipo deve ser escolhido.")
    private TipoItem tipo;

    @NotBlank(message = "Livro deve possuir alguma categoria.")
    private String categoria;

    @NotNull(message = "Livro deve possuir classificação indicativa.")
    private ClassificacaoIndicativa classificacaoIndicativa;

    @NotNull(message = "Ano de publicação não pode ser Null")
    @Max(value = 2026, message = "Ano de publicação não pode ser do futuro")
    @PositiveOrZero
    private Integer anoPublicacao;

    @NotNull(message = "Quantidade de livros não pode ser Null")
    @PositiveOrZero(message = "Quantidade de livros não pode ser negativo.")
    private int quantidadeDisponivel;
}
