package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService service = new SalaService();

    @GetMapping
    public List<Sala> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = service.buscarPorId(id);
        if (sala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sala);
    }
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Sala sala) {
        try {
            service.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body(sala);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Sala sala) {
        try {
            service.alterar(id, sala);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}