package com.mercadolibre.ismutant.controller;

import com.mercadolibre.ismutant.DataBuilder;
import com.mercadolibre.ismutant.service.MutantAnalyzeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantAnalyzeControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MutantAnalyzeService analyzeService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn200WhenSendValidDnaSequence() throws Exception {
        Mockito.when(analyzeService.isMutant(Mockito.any())).thenReturn(Boolean.TRUE);
        mvc.perform(post("/mutant").content(DataBuilder.getValidHumanPayload())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn403WhenSendValidDnaSequence() throws Exception {
        Mockito.when(analyzeService.isMutant(Mockito.any())).thenReturn(Boolean.FALSE);
        mvc.perform(post("/mutant").content(DataBuilder.getIValidHumanPayload())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
    }
}
