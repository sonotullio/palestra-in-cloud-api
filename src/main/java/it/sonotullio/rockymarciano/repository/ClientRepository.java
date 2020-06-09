package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findByEmailAndPassword(String email, String password);
}
