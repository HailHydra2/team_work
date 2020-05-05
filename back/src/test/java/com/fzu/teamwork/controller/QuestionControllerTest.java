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
public class QuestionControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetQuestionPage() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testAddQuestion() throws Exception {
        String json = "{" +
                "\"title\":\"title\"," +
                "\"content\":\"content\"," +
                "\"question\": {" +
                                    "\"id\": 30," +
                                    "\"authorId\": 2," +
                                    "\"accountId\": 1," +
                                    "\"responseNum\": 0," +
                                    "\"reportNum\": 0," +
                                    "\"createTime\": \"2020-05-02 01:00:12\"," +
                                    "\"titleId\": \"22\"," +
                                    "\"contentId\":55" +
                                "}" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/question")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetUserQuestionPage() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/userQuestions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetAttentionQuestionPage() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/userAttentions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public  void TestGetUserResponseQuestion() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/userResponseQuestions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/question/26/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void GetQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/question/26"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/question/31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void deleteQuestions() throws Exception {
        String json = "[32,33]";
        mockMvc.perform(MockMvcRequestBuilders.delete("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

}
