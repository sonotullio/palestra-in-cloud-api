package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, String> {

    List<Purchase> findAllByClientId(int clientId);
    List<Purchase> findAllByProductId(String productId);
}
