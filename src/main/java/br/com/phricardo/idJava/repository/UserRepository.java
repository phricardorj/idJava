package br.com.phricardo.idJava.repository;

import br.com.phricardo.idJava.model.UserModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    public Optional<UserModel> findByUsername(String username);
    public Optional<UserModel> findByEmail(String email);
}
