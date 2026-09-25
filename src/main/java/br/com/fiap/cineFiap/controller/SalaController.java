package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private SalaDAO dao = new SalaDAO();
    private SalaService salaService = new SalaService();

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@RequestBody Sala sala) {

        Sala salaCadastrada = salaService.cadastrar(sala);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salaCadastrada);
    }

    @GetMapping
    public List<Sala> listar() {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {

        Sala sala = salaService.buscarPorId(id);

        if (sala != null) {
            return ResponseEntity.ok(sala);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(
            @PathVariable Long id,
            @RequestBody Sala sala) {

        try {

            salaService.alterar(id, sala);

            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}