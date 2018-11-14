package com.mercadolibre.ismutant.controller;

import com.mercadolibre.ismutant.dto.Human;
import com.mercadolibre.ismutant.exception.AnalyzeMutantException;
import com.mercadolibre.ismutant.service.MutantAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantAnalyzeController {

    @Autowired
    private MutantAnalyzeService analyzeService;

    @PostMapping
    public ResponseEntity<Void> isMutant(@RequestBody Human human) throws AnalyzeMutantException {
        if (analyzeService.isMutant(human.getDna())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
