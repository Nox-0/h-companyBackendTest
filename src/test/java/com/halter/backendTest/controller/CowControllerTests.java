package com.halter.backendTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halter.backendTest.TestApplication;
import com.halter.backendTest.controller.CowController;
import com.halter.backendTest.models.CollarStatus;
import com.halter.backendTest.models.Cow;
import com.halter.backendTest.models.requests.NewCowRequest;
import com.halter.backendTest.repository.CowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CowController.class)
@ContextConfiguration(classes = TestApplication.class)
public class CowControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private CowController cowController;
    @MockBean
    private CowRepository cowRepository;

    private NewCowRequest newCowRequest1;
    private NewCowRequest newCowRequest2;
    private Cow cow1;
    private Cow cow2;


    @BeforeEach
    void setup() {
        newCowRequest1 = new NewCowRequest(261, 88261, CollarStatus.HEALTHY);
        newCowRequest2 = new NewCowRequest(222, 88222, CollarStatus.BROKEN);

        cow1 = new Cow(newCowRequest1);
        cow2 = new Cow(newCowRequest2);
    }

    @Test
    void testPostCow_returnCreated() throws Exception {
        mvc.perform(post("/cows")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newCowRequest1)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCows_returnOk() throws Exception {
        mvc.perform(get("/cows"))
                .andExpect(status().isOk());
    }

    @Test
    void testPutCows_invalidCowId_returnBadRequest() throws Exception {
        Mockito.when(cowRepository.findById(cow1.getId())).thenReturn(null);

        mvc.perform(put("/cows/{id}", cow1.getId())
                .contentType("application/json")
                .content(mapper.writeValueAsString(newCowRequest2)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void testPutCows_returnOk() throws Exception {
        Mockito.when(cowRepository.findById(cow1.getId())).thenReturn(cow1);

        mvc.perform(put("/cows/{id}", cow1.getId())
                .contentType("application/json")
                .content(mapper.writeValueAsString(newCowRequest2)))
                .andExpect(status().isOk());
    }
}
