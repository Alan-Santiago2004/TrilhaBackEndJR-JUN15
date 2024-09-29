package com.codigoCerto.tarefas.repositories;

import com.codigoCerto.tarefas.models.UserModel;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    UserDetails findByLogin(String login);
}
