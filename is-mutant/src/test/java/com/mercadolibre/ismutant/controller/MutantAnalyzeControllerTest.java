package com.mercadolibre.ismutant.controller;

import com.mercadolibre.ismutant.DataBuilder;
import com.mercadolibre.ismutant.service.MutantAnalyzeService;
import com.mercadolibre.ismutant.service.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantAnalyzeControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MutantAnalyzeService analyzeService;

    @Autowired
    private StatsService statsService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn200WhenSendValidDnaSequence() throws Exception {
        mvc.perform(post("/mutant").content(DataBuilder.getValidHumanPayload())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn403WhenSendValidDnaSequence() throws Exception {
        mvc.perform(post("/mutant").content(DataBuilder.getIValidHumanPayload())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldReturnStats() throws Exception {
        mvc.perform(get("/stats")).andExpect(status().isOk());
    }


}
