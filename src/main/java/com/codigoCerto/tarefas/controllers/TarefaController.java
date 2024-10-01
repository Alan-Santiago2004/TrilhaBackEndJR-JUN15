package com.codigoCerto.tarefas.controllers;

import com.codigoCerto.tarefas.dtos.TarefaDTO;
import com.codigoCerto.tarefas.models.TarefaModel;
import com.codigoCerto.tarefas.repositories.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping("/tarefa")
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO){
        TarefaModel tarefa = new TarefaModel();
        BeanUtils.copyProperties(tarefaDTO,tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
    }


    @GetMapping("/tarefa")
    public ResponseEntity<List<TarefaModel>> ListarTarefas(){
        List<TarefaModel> tarefaList = tarefaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tarefaList);
    }

    @GetMapping("/tarefa/{id}")
    public ResponseEntity<Object> selecionarTarefa(@PathVariable(value = "id")UUID uuid){
        Optional<TarefaModel> tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarefa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa.get());
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<Object> atualizarTarefa(@PathVariable(value = "id")UUID uuid,
                                                    @RequestBody @Valid TarefaDTO tarefaDTO){
        Optional<TarefaModel> tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarefa não encontrada");
        }
        TarefaModel novaTarefa = tarefa.get();
        BeanUtils.copyProperties(tarefaDTO,novaTarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(novaTarefa));
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<Object> DeletarTarefa(@PathVariable(value = "id")UUID uuid){
        Optional<TarefaModel> tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarefa não encontrada");
        }
        tarefaRepository.delete(tarefa.get());
        return ResponseEntity.status(HttpStatus.OK).body("tarefa deletada");
    }
}
