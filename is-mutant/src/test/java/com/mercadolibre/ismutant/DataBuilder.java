package com.mercadolibre.ismutant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.ismutant.dto.Human;

public class DataBuilder {

    public static String getValidHumanPayload() throws JsonProcessingException {
        Human validSequence = new Human();
        validSequence.setDna(new String[]{"ATTAG", "ATTAG", "ATTCA", "ATCCA"});
        return new ObjectMapper().writeValueAsString(validSequence);
    }

    public static String getIValidHumanPayload() throws JsonProcessingException {
        Human invalidSequence = new Human();
        invalidSequence.setDna(new String[]{"ATTAG", "ATTAG", "ATTCA", "ATCCA"});
        return new ObjectMapper().writeValueAsString(invalidSequence);
    }
}
