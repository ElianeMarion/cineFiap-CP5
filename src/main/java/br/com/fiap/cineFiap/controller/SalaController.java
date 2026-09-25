package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private SalaService salaService = new SalaService();

    @GetMapping
    public ResponseEntity<List<Sala>> listar(){
        return ResponseEntity.ok(salaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable long id){
        Sala sala = new Sala();
        if (sala.getId() != null){
            return ResponseEntity.ok(salaService.buscaPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

}
