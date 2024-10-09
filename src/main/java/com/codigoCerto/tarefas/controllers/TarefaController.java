package com.codigoCerto.tarefas.controllers;

import com.codigoCerto.tarefas.dtos.TarefaDTO;
import com.codigoCerto.tarefas.models.TarefaModel;
import com.codigoCerto.tarefas.repositories.TarefaRepository;
import com.codigoCerto.tarefas.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "tarefas" , description = "Controlador para listar, criar, deletar e editar tarefas")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping("/tarefa")
    @Operation(summary = "salvar uma nova tarefa", description = "Método para salvar uma nova tarefa no banco de dados," +
            "essa requisição só pode ser executada por um usuario do tipo ADMIN")
    @ApiResponse(responseCode = "201", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não autenticado ou não é do tipo ADMIN")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO){
        TarefaModel tarefa = new TarefaModel();
        BeanUtils.copyProperties(tarefaDTO,tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
    }


    @GetMapping("/tarefa")
    @Operation(summary = "listar tarefas", description = "Método responsavel por chamar uma lista com todas as tarefas," +
            "essa requisição só pode ser executada por um usuario do tipo USER e ADMIN que esteja logado")
    @ApiResponse(responseCode = "200", description = "Requisição executada com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não autenticado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefaModel>> ListarTarefas(){
        List<TarefaModel> tarefaList = tarefaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tarefaList);
    }

    @GetMapping("/tarefa/{id}")
    @Operation(summary = "selecionar tarefa", description = "Método responsavel por chamar apenas uma tarefa expecifica," +
            "essa requisição só pode ser executada por um usuario do tipo USER e ADMIN que esteja logado")
    @ApiResponse(responseCode = "200", description = "Requisição executada com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não autenticado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Object> selecionarTarefa(@PathVariable(value = "id")UUID uuid){
        Optional<TarefaModel> tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarefa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa.get());
    }

    @PutMapping("/tarefa/{id}")
    @Operation(summary = "atualizar uma tarefa", description = "Método responsavel por atualizar apenas uma tarefa expecifica," +
            "essa requisição só pode ser executada por um usuario do tipo ADMIN")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não autenticado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
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
    @Operation(summary = "deletar uma tarefa", description = "Método responsavel por deletar apenas uma tarefa expecifica," +
            "essa requisição só pode ser executada por um usuario do tipo ADMIN")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não autenticado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    public ResponseEntity<Object> DeletarTarefa(@PathVariable(value = "id")UUID uuid){
        Optional<TarefaModel> tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tarefa não encontrada");
        }
        tarefaRepository.delete(tarefa.get());
        return ResponseEntity.status(HttpStatus.OK).body("tarefa deletada");
    }
}
