package com.mercadolibre.ismutant.controller;

import com.mercadolibre.ismutant.dto.Human;
import com.mercadolibre.ismutant.dto.StatsDTO;
import com.mercadolibre.ismutant.service.MutantAnalyzeService;
import com.mercadolibre.ismutant.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantAnalyzeController {

    @Autowired
    private MutantAnalyzeService analyzeService;

    @Autowired
    private StatsService statsService;

    @PostMapping
    @RequestMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody Human human) {
        if (analyzeService.isMutant(human.getDna())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping
    @RequestMapping("/stats")
    public ResponseEntity<StatsDTO> getStats() {
        return new ResponseEntity<>(statsService.stats(), HttpStatus.OK);
    }

}
