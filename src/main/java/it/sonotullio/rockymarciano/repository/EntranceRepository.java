package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Entrance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntranceRepository extends CrudRepository<Entrance, String> {
    List<Entrance> findAllByClientId(int clientId);
}
