package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.domain.Stats;
import com.mercadolibre.ismutant.dto.StatsDTO;

public interface StatsService {
    Stats save(Stats stats);
    StatsDTO stats();
}
