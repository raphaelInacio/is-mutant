package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;
import org.junit.Assert;
import org.junit.Test;

public class MutantAnalyzeServiceImplTest {

    private MutantAnalyzeServiceImpl mutantAnalize = new MutantAnalyzeServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenInvalidDNAisFinded() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[] { "AAAA", "CCCC", "TCAC10(*&@#", "123123" });
    }

}
