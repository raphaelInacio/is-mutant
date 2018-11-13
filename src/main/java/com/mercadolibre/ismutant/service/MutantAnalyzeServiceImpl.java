package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MutantAnalyzeServiceImpl implements MutantAnalyzeService {

    private static final Pattern dnaMatchPattern = Pattern.compile("(C{4}|A{4}|T{4}|G{4})");
    private static final Pattern noDnaMatch = Pattern.compile("(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])");
    private static final long baseNumberSequenceGene = 1;

    @Override
    public boolean isMutant(String[] dna) throws AnalyzeMutantException {
        List<String> dnaGenes = Arrays.asList(dna);
        validateInputGenes(dnaGenes);
        return getOccurrencesInHorizontal(dnaGenes) > baseNumberSequenceGene;
    }

    private long getOccurrencesInHorizontal(List<String> dna) {
        return dna.stream().filter(gene -> dnaMatchPattern.matcher(gene).find()).count();
    }

    private void validateInputGenes(List<String> dna) {

        if (dna.isEmpty()) {
            throw new IllegalArgumentException("Invalid fields founded");
        }

        containInValidGenes(dna);
    }

    private void containInValidGenes(List<String> dna) {
        dna.stream().filter(gene -> noDnaMatch.matcher(gene).find()).findFirst().ifPresent(s -> {
            throw new IllegalArgumentException("Invalid fields founded");
        });
    }
}
