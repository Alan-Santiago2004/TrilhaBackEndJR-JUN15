package com.codigoCerto.tarefas.dtos;

import com.codigoCerto.tarefas.models.enums.RoleUser;

public record RegisterDTO(String login, String password, RoleUser role) {
}
