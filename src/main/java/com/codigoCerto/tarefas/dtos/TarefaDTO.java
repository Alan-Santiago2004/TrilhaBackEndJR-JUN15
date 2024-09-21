package com.codigoCerto.tarefas.dtos;


import jakarta.validation.constraints.NotBlank;

public record TarefaDTO(@NotBlank String tarefaCorpo){
}
