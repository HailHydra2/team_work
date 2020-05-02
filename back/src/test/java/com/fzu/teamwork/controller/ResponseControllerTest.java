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
public class ResponseControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/response/50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetResponsePage() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/responses/30")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void GetResponsePage() throws Exception {
        String json = "{" +
                "\"pageIndex\":5," +
                "\"count\":5," +
                "\"keyword\":\"\"," +
                "\"sortApproach\":\"sortByDate\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/responses/30/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testDeleteResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/testResponse/46"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testGetResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/testResponse/50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void deleteResponseList() throws Exception {
        String json = "[47,3348]";
        mockMvc.perform(MockMvcRequestBuilders.delete("/responses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addResponse() throws Exception {
        String json = "{" +
                "\"content\":\"content\"," +
                "\"response\": " +
                            "{" +
                                "\"id\":50," +
                                "\"questionId\":30," +
                                "\"authorId\":1," +
                                "\"contentId\":54," +
                                "\"likeNum\":0," +
                                "\"dislikeNum\":0," +
                                "\"reportNum\":0," +
                                "\"createTime\":\"2020-05-02 01:00:12\"" +
                            "}" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/response")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }
}
