package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

}
