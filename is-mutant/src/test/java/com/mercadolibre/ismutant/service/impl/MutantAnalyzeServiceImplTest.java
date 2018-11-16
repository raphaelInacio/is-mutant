package com.mercadolibre.ismutant.service.impl;

import com.mercadolibre.ismutant.domain.Origin;
import com.mercadolibre.ismutant.domain.Stats;
import com.mercadolibre.ismutant.exception.AnalyzeMutantException;
import com.mercadolibre.ismutant.service.StatsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MutantAnalyzeServiceImplTest {

    @InjectMocks
    private MutantAnalyzeServiceImpl mutantAnalize;

    @Mock
    private StatsService statsService;

    @Test(expected = AnalyzeMutantException.class)
    public void shouldTrowAnalyzeMutantExceptionWhenInvalidDNAisFinded() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[]{"AAAA", "CCCC", "TCAC10(*&@#", "123123"});
    }

    @Test(expected = AnalyzeMutantException.class)
    public void shouldTrowAnalyzeMutantExceptionWhenInvalidDNAEmpty() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[]{});
    }

    @Test
    public void shouldReturnFalseWhenSequenceAIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[]{"AAA"}));
    }

    @Test
    public void shouldReturnFalseWhenSequenceCIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[]{"CCC"}));
    }

    @Test
    public void shouldReturnFalseWhenSequenceTIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[]{"TTT"}));
    }

    @Test
    public void shouldReturnFalseWhenSequenceGIsMinorThanFourOccurrences() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[]{"GG"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceAIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{"AAAA", "AAAA"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceCIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{"CCCC", "CCCC"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceTIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{"TTTT", "TTTT"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceGIsFindInHorizontalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{"GGGG", "GGGG"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceAIsFindInVerticalGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{
                "ATTAG",
                "ATTAG",
                "ATTCA",
                "ATCCA"}));
    }

    @Test
    public void shouldReturnFalseWhenSequenceAIsInVerticalIsEqualsOneOccurrence() throws AnalyzeMutantException {
        Assert.assertFalse(mutantAnalize.isMutant(new String[]{
                "ATTAG",
                "ATTAG",
                "ACTCA",
                "ACCCA"}));
    }

    @Test
    public void shouldReturnTrueWhenSequenceTIsFindInDiagonalIsGraterThanOneOccurrence() throws AnalyzeMutantException {
        Assert.assertTrue(mutantAnalize.isMutant(new String[]{
                "TATT",
                "ATCT",
                "ATTT",
                "ACCT"}));
    }


    @Test
    public void shouldSaveMutantOccurrenceSuccessfully() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[]{
                "TATT",
                "ATCT",
                "ATTT",
                "ACCT"});

        Mockito.verify(statsService, Mockito.times(1)).save(Mockito.eq(new Stats(Origin.MUTANT)));
    }

    @Test
    public void shouldSaveHumanOccurrenceSuccessfully() throws AnalyzeMutantException {
        mutantAnalize.isMutant(new String[]{
                "TATT",
                "AACT",
                "ATCT",
                "ACCC"});

        Mockito.verify(statsService, Mockito.times(1)).save(Mockito.eq(new Stats(Origin.HUMAN)));
    }

}
