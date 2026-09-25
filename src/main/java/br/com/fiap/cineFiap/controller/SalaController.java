package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Filme;
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
    private final SalaService service;

    public SalaController(){this.service = new SalaService();}

    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable Integer Id){
        return service.buscarPorId(id);}
    @PostMapping
    public void cadastrar(@RequestBody Sala sala){
        SalaService.cadastrar(sala);
    }
    @PutMapping
    public Filme alterar(Sala sala){
        return service.alterar(sala);
    }
    @PutMapping
    public Filme delete(Sala sala)
        return SalaService.delete(sala);

}

