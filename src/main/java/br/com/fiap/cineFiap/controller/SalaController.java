package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import br.com.fiap.cineFiap.dao.SalaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService salaService = new SalaService();


    public void cadastrar( Sala sala){

            dao.cadastrar(sala);

    }

    @GetMapping("/{id}")
    public ResponseEntity <Sala> buscarPorId (@PathVariable Long id){

        return ResponseEntity.ok(sala);


    }

    @GetMapping
    public List<Sala> salasEmCartaz(){
        return salaService.listar();
    }


    public void excluir ( Long id){

            dao.excluir(id);

    }


    public void alterar( Long id, Sala objeto){

            dao.alterar(objeto);
    }


    public void deletar(@PathVariable Long id){

            dao.deletar(id);


    }
}
