package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import br.com.fiap.cineFiap.dao.SalaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService salaService = new SalaService();

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Sala sala) {
        try {
            Sala novaSala = salaService.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity <Sala> buscarPorId (@PathVariable Long id){
        Sala sala = salaService.buscarPorId(id);

        if (sala == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sala);
    }

    @GetMapping
    public List<Sala> salasEmCartaz(){
        return salaService.listar();
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            salaService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Sala objeto) {
        try {
            Sala atualizada = salaService.alterar(id, objeto);
            return ResponseEntity.ok(atualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
