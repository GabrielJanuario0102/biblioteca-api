package br.edu.ifpb.biblioteca_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.biblioteca_api.exception.ItemNaoEncontradoException;
import br.edu.ifpb.biblioteca_api.model.ItemBiblioteca;
import br.edu.ifpb.biblioteca_api.repository.ItemBibliotecaRepository;

@Service
public class ItemBibliotecaService {

    private final ItemBibliotecaRepository repository;

    public ItemBibliotecaService(ItemBibliotecaRepository repository) {
        this.repository = repository;
    }

    public List<ItemBiblioteca> listarTodos() {
        return repository.findAll();
    }

    public ItemBiblioteca buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado. ID:" + id));
    }

    public ItemBiblioteca salvar(ItemBiblioteca itemBiblioteca) {
        return repository.save(itemBiblioteca);
    }

    public ItemBiblioteca atualizar(String id, ItemBiblioteca ib) {
        ItemBiblioteca ibExistente = buscarPorId(id);
        ibExistente.setTitulo(ib.getTitulo());
        ibExistente.setAutor(ib.getAutor());
        ibExistente.setIsbn(ib.getIsbn());
        ibExistente.setEditora(ib.getEditora());
        ibExistente.setTipo(ib.getTipo());
        ibExistente.setCategoria(ib.getCategoria());
        ibExistente.setClassificacaoIndicativa(ib.getClassificacaoIndicativa());
        ibExistente.setAnoPublicacao(ib.getAnoPublicacao());
        ibExistente.setQuantidadeDisponivel(ib.getQuantidadeDisponivel());

        return repository.save(ibExistente);
    }

    public void deletar(String id) {
        ItemBiblioteca ib = buscarPorId(id);

        repository.delete(ib);
    }
}