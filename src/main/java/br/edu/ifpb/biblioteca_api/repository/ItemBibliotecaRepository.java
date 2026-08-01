package br.edu.ifpb.biblioteca_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.ifpb.biblioteca_api.model.ItemBiblioteca;

public interface ItemBibliotecaRepository extends MongoRepository<ItemBiblioteca, String> {
    
}
