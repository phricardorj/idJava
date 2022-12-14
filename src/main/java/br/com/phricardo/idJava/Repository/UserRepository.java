package br.com.phricardo.idJava.Repository;

import br.com.phricardo.idJava.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Override
    Optional<User> findById(UUID uuid);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}