package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.Service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private SalaService service = new SalaService();

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
    public ResponseEntity<Sala> cadastrar(@RequestBody Sala sala) {
        service.cadastrar(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(sala);
    }

}