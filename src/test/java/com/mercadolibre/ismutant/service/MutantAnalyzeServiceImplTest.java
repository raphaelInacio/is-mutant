package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;
import org.junit.Assert;
import org.junit.Test;

public class MutantAnalyzeServiceImplTest {

    private MutantAnalyzeServiceImpl mutantAnalize = new MutantAnalyzeServiceImpl();

    @Test(expected = AnalyzeMutantException.class)
    public void shouldTrowAnalizeExceptionWhenInvalidDNAisFinded() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[]{"AGAAGG", "AGAAGA"});
    }

    @Test
    public void shouldFindMutandWhenDNAisFindedInHorizontalSend() throws AnalyzeMutantException {
        boolean mutant = mutantAnalize.isMutant(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        Assert.assertTrue(mutant);
    }
}