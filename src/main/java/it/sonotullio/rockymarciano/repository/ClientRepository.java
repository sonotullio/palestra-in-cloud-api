package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
