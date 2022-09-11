package br.com.phricardo.idJava.repository;

import br.com.phricardo.idJava.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    public Optional<UserModel> findByUsername(String username);
    public Optional<UserModel> findByEmail(String email);
}
