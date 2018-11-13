package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.service.exception.AnalyzeMutantException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class MutantAnalyzeServiceImpl implements MutantAnalyzeService {

    private static final Pattern dnaMatchPattern = Pattern.compile("(C{4}|A{4}|T{4}|G{4})");
    private static final Pattern noDnaMatch = Pattern.compile("(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])");
    private static final long baseNumberSequenceGene = 1;
    private static final String EMPTY_STRING = "";

    @Override
    public boolean isMutant(String[] dna) throws AnalyzeMutantException {
        List<String> dnaGenes = Arrays.asList(dna);
        long totalOccurrences;
        validateInputGenes(dnaGenes);
        totalOccurrences = getOccurrences(dnaGenes);
        totalOccurrences += getOccurrences(collectItemsInVertical(dnaGenes));
        return totalOccurrences > baseNumberSequenceGene;
    }

    private List<String> collectItemsInVertical(List<String> dnaGenes) {

        Map<Long, String> horizontalMap = new HashMap<>();

        for (String dnaGene : dnaGenes) {

            AtomicLong count = new AtomicLong();

            Stream.of(dnaGene.split(EMPTY_STRING)).forEach(newValue -> {

                if (horizontalMap.containsKey(count.get())) {
                    String oldValue = horizontalMap.get(count.get());
                    horizontalMap.put(count.get(), String.join(EMPTY_STRING, oldValue, newValue));
                } else {
                    horizontalMap.put(count.get(), newValue);
                }

                count.incrementAndGet();

            });
        }

        return new ArrayList(horizontalMap.values());
    }

    private long getOccurrences(List<String> dna) {
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
            throw new IllegalArgumentException("Invalid fields founded:" + s);
        });
    }
}
