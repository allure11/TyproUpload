package com.springzym;

import com.springzym.core.CoreApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@SpringBootTest(classes = CoreApplication.class)
public class FileControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void TestGetAll() throws Exception {
//        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/file/getAll"));
//        String contentAsString = perform.andReturn().getResponse().getContentAsString();

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/file/getAll")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        // 解决中文乱码问题
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn()
                        .getResponse()
                        .getContentAsString(Charset.defaultCharset());

        System.out.println(contentAsString);
    }

}
