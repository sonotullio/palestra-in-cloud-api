package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends CrudRepository<Sport, String> {
}
