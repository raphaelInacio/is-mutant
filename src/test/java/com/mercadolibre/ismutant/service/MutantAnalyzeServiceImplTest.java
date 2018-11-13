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

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenInvalidDNAEmpty() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[] {});
    }

    @Test
    public void shouldReturnFalseWhenSequenceAIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[] { "AAA" }));
    }

    @Test
    public void shouldReturnFalseWhenSequenceCIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[] { "CCC" }));
    }

    @Test
    public void shouldReturnFalseWhenSequenceTIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[] { "TTT" }));
    }

    @Test
    public void shouldReturnFalseWhenSequenceGIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[] { "GG" }));
    }

    @Test
    public void shouldReturnTrueWhenSequenceAIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[] { "AAAA", "AAAA" }));
    }

    @Test
    public void shouldReturnTrueWhenSequenceCIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[] { "CCCC", "CCCC" }));
    }

    @Test
    public void shouldReturnTrueWhenSequenceTIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[] { "TTTT", "TTTT" }));
    }

    @Test
    public void shouldReturnTrueWhenSequenceGIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[] { "GGGG", "GGGG" }));
    }

    @Test
    public void shouldReturnTrueWhenSequenceAIsFindInVerticalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[] {
        "ATTAG",
        "ATTAG",
        "ATTCA",
        "ATCCA" }));
    }

}
