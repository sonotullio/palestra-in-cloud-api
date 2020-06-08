package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByCf(String cf);
    User findByCfAndPassword(String cf, String password);
}
