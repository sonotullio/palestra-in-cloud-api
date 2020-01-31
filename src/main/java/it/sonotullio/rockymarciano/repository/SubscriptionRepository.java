package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    List<Subscription> findAllByClientId(int clientId);
}
