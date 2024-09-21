package com.codigoCerto.tarefas.models;

import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "tb_tarefa")
public class TarefaModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tarefaID;
    private String tarefaCorpo;

    public TarefaModel() {
    }

    public TarefaModel(UUID tarefaID, String tarefaCorpo) {
        this.tarefaID = tarefaID;
        this.tarefaCorpo = tarefaCorpo;
    }

    public UUID getTarefaID() {
        return tarefaID;
    }

    public String getTarefaCorpo() {
        return tarefaCorpo;
    }

    public void setTarefaCorpo(String tarefaCorpo) {
        this.tarefaCorpo = tarefaCorpo;
    }
}
