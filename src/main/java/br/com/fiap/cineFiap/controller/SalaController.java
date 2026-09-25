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
        Sala sala = salaService.buscaPorId(id);
        if (sala.getId() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salaService.buscaPorId(id));
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Sala sala){
        try{
            salaService.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sala cadastrada com SUCESSO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sala não cadastrada. Erro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@RequestBody Sala sala, @PathVariable long id){
        try{
            salaService.alterar(sala, id);
            return ResponseEntity.status(HttpStatus.OK).body("Sala alterada com SUCESSO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sala não alterada. Erro: " + e.getMessage());
        }
    }
}
