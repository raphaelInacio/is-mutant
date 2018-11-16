package com.mercadolibre.ismutant.service.impl;

import com.mercadolibre.ismutant.domain.Origin;
import com.mercadolibre.ismutant.domain.Stats;
import com.mercadolibre.ismutant.dto.StatsDTO;
import com.mercadolibre.ismutant.repository.StatsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceImplTest {

    @InjectMocks
    private StatsServiceImpl statsService;

    @Mock
    private StatsRepository repository;

    @Test
    public void shouldSaveMutant() {
        statsService.save(new Stats(Origin.MUTANT));
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.eq(new Stats(Origin.MUTANT)));
    }


    @Test
    public void shouldSaveHuman() {
        statsService.save(new Stats(Origin.HUMAN));
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.eq(new Stats(Origin.HUMAN)));
    }

    @Test
    public void shouldCalcStats() {
        Mockito.when(repository.countByOrigin(Origin.MUTANT)).thenReturn(17L);
        Mockito.when(repository.countByOrigin(Origin.HUMAN)).thenReturn(15L);
        StatsDTO stats = statsService.stats();
        Assert.assertEquals(new BigDecimal("0.53"), stats.getRatio());
    }

    @Test
    public void shouldReturnZeroRatioWhenNotFindMutants() {
        Mockito.when(repository.countByOrigin(Origin.MUTANT)).thenReturn(0L);
        Mockito.when(repository.countByOrigin(Origin.HUMAN)).thenReturn(15L);
        StatsDTO stats = statsService.stats();
        Assert.assertEquals(BigDecimal.ZERO, stats.getRatio());
    }
}