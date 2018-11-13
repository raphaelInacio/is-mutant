package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;

public interface MutantAnalyzeService {
    boolean isMutant(String[] dna) throws AnalyzeMutantException;
}
