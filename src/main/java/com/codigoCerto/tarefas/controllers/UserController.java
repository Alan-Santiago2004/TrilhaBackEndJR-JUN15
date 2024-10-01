package com.codigoCerto.tarefas.controllers;

import com.codigoCerto.tarefas.models.UserModel;
import com.codigoCerto.tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserModel>> ListarTarefas(){
        List<UserModel> usuarios = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
}
