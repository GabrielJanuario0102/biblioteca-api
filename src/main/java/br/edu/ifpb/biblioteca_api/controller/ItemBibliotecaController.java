package br.edu.ifpb.biblioteca_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpb.biblioteca_api.model.ItemBiblioteca;
import br.edu.ifpb.biblioteca_api.service.ItemBibliotecaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500"
})
public class ItemBibliotecaController {

    private final ItemBibliotecaService service;

    public ItemBibliotecaController(ItemBibliotecaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemBiblioteca>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemBiblioteca> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemBiblioteca> salvar(
            @Valid @RequestBody ItemBiblioteca itemBiblioteca) {

        ItemBiblioteca itemSalvo = service.salvar(itemBiblioteca);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemBiblioteca> atualizar(
            @PathVariable String id,
            @Valid @RequestBody ItemBiblioteca itemBiblioteca) {

        ItemBiblioteca itemAtualizado = service.atualizar(id, itemBiblioteca);

        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

}