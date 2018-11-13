package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MutantAnalyzeServiceImpl implements MutantAnalyzeService {

    private static final List<String> VALID_GENES = Arrays.asList("A", "T", "C", "G");
    private static final Pattern dnaMatchPattern = Pattern.compile("(C{4}|A{4}|T{4}|G{4})");
    private static final Pattern noDnaMatch = Pattern.compile("(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])");

    @Override
    public boolean isMutant(String[] dna) throws AnalyzeMutantException {
        List<String> dnaGenes = Arrays.asList(dna);
        containsInValidGenes(dnaGenes);
        return Boolean.TRUE;
    }

    private void containsInValidGenes(List<String> dna) {
        dna.stream().filter(item -> noDnaMatch.matcher(item).find()).findFirst().ifPresent(s -> {
            throw new IllegalArgumentException("Invalid fields founded");
        });
    }
}
