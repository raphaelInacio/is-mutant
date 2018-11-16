package com.mercadolibre.ismutant.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@Getter
@Setter
@Document("stats")
public class Stats {

    @Id
    private String id;
    private Origin origin;

    public Stats(Origin origin) {
        this.origin = origin;
    }
}
