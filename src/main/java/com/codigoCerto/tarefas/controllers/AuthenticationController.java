package com.codigoCerto.tarefas.controllers;

import com.codigoCerto.tarefas.dtos.AuthDTO;
import com.codigoCerto.tarefas.dtos.LoginResponseDTO;
import com.codigoCerto.tarefas.dtos.RegisterDTO;
import com.codigoCerto.tarefas.models.UserModel;
import com.codigoCerto.tarefas.repositories.UserRepository;
import com.codigoCerto.tarefas.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel user = new UserModel(data.login(),encryptedPassword,data.role());
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }
}
