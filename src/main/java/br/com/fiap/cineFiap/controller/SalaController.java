package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService service;

    public SalaController() {
        this.service = new SalaService();
    }

    @GetMapping
    public ResponseEntity<List<Sala>> salasAtivas(){
        return ResponseEntity.ok(service.salasAtivas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        var sala = service.buscarPorId(id);
        if(sala.getId() != null && sala.getDataExclusao() == null)
            return ResponseEntity.ok(sala);
        return ResponseEntity.notFound().build();
    }
}