package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}