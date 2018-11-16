package com.mercadolibre.ismutant.repository;

import com.mercadolibre.ismutant.domain.Origin;
import com.mercadolibre.ismutant.domain.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends MongoRepository<Stats, String> {
    long countByOrigin(Origin origin);
}
