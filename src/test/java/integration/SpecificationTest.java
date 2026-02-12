package integration;

import org.junit.jupiter.api.Test;
import org.springdoc.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import polymorphism.Main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class SpecificationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void getSpecificationTest() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL)).andExpect(status().isOk())
                .andExpect(jsonPath("$.openapi", is("3.1.0"))).andReturn();
        String result = mockMvcResult.getResponse().getContentAsString();
        String expected = getContent("specification.json");
        assertEquals(expected, result, true);
    }

    private String getContent(String fileName) {
        try {
            Path path = Paths.get(SpecificationTest.class.getClassLoader().getResource(fileName).toURI());
            byte[] fileBytes = Files.readAllBytes(path);
            return new String(fileBytes, StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }
    }

}

