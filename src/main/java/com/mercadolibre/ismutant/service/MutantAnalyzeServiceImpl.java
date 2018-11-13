package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;

import java.util.Arrays;
import java.util.List;

public class MutantAnalyzeServiceImpl implements MutantAnalyzeService {

    private static final List<String> VALID_GENES = Arrays.asList("A", "T", "C", "G");

    @Override
    public boolean isMutant(String[] dna) throws AnalyzeMutantException {
        List<String> dnaGenes = Arrays.asList(dna);
        isValidGenes(dnaGenes);
        return Boolean.FALSE;
    }

    private void isValidGenes(List<String> dna) throws AnalyzeMutantException {
    }
}
