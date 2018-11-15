package com.mercadolibre.ismutant.service;

import com.mercadolibre.ismutant.exception.AnalyzeMutantException;
import com.mercadolibre.ismutant.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class MutantAnalyzeServiceImpl implements MutantAnalyzeService {

    private static final Logger logger = LoggerFactory.getLogger(MutantAnalyzeServiceImpl.class);

    private List<String> collectItemsInDiagonal(List<String> dnaGenes) {
        logger.info("collect items in diagonal...'");
        return Arrays.asList(IntStream
        .range(0, dnaGenes.size())
        .mapToObj(i -> dnaGenes.get(i).split(Util.EMPTY_STRING)[i])
        .collect(Collectors.joining()));
    }

    private List<String> collectItemsInVertical(List<String> dnaGenes) {

        logger.info("collect items in vertical...'");

        Map<Long, String> horizontalMap = new HashMap<>();

        for (String dnaGene : dnaGenes) {

            AtomicLong count = new AtomicLong();

            Stream.of(dnaGene.split(Util.EMPTY_STRING)).forEach(newValue -> {

                if (horizontalMap.containsKey(count.get())) {
                    String oldValue = horizontalMap.get(count.get());
                    horizontalMap.put(count.get(), String.join(Util.EMPTY_STRING, oldValue, newValue));
                } else {
                    horizontalMap.put(count.get(), newValue);
                }

                count.incrementAndGet();

            });
        }

        return new ArrayList(horizontalMap.values());
    }

    private long getOccurrences(List<String> dna) {
        logger.info("Verifying occurrences...'");
        return dna.stream().filter(gene -> Util.dnaMatchPattern.matcher(gene).find()).count();
    }

    private void validateInputGenes(List<String> dna) {
        logger.info("Validating input...'");
        if (dna.isEmpty()) {
            throw new IllegalArgumentException("Human are empty!");
        }

        containInValidGenes(dna);
    }

    private void containInValidGenes(List<String> dna) {
        dna.stream().filter(gene -> Util.noDnaMatch.matcher(gene).find()).findFirst().ifPresent(s -> {
            throw new IllegalArgumentException("Invalid fields founded:" + s);
        });
    }

    @Override
    public boolean isMutant(String[] dna) throws AnalyzeMutantException {

        try {

            logger.info("Stating analyze mutant...'");

            List<String> dnaGenes = Arrays.asList(dna);
            long totalOccurrences;
            validateInputGenes(dnaGenes);
            totalOccurrences = getOccurrences(dnaGenes);
            totalOccurrences += getOccurrences(collectItemsInVertical(dnaGenes));
            totalOccurrences += getOccurrences(collectItemsInDiagonal(dnaGenes));

            logger.info("Occurrences founded, {}", totalOccurrences);

            return totalOccurrences > Util.baseNumberSequenceGene;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AnalyzeMutantException(e.getMessage());
        }
    }
}
