package com.sunlife.vn.functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.sunlife.vn.handlers.FunctionApplication;
import com.sunlife.vn.models.TaskRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FunctionApplication.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureJsonTesters
public class FunctionTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<TaskRequest> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void testFunction() throws Exception {
    }
}
