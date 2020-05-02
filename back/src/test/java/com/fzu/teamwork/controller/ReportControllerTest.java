package com.fzu.teamwork.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetReportQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/questionReports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetReportResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/responseReports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addQuestionReport() throws Exception {
        String json = "{" +
                "\"id\":7," +
                "\"reportorId\":2," +
                "\"questionId\":26," +
                "\"flag\":0" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/questionReport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addResponseReport() throws Exception {
        String json = "{" +
                "\"id\":8," +
                "\"reportorId\":2," +
                "\"responseId\":49," +
                "\"flag\":0" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/responseReport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }
}
