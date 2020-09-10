package org.blog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by melchor
 * Date: 2020/09/10
 * Time: 9:18 AM
 */

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BasedTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")).build();
    }

}
