package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);
}
