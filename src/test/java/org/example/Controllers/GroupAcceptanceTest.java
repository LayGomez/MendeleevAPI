package org.example.Controllers;

import org.example.entities.ElementGroup;
import org.example.repositories.ElementGroupRepository;
import org.example.repositories.ElementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GroupAcceptanceTest {

    @Autowired
    private ElementGroupRepository elementGroupRepository;


    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ElementGroup group1 = new ElementGroup(1, "Reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        elementGroupRepository.save(group1);
    }

    @Test
    void givenAValidGroup_whenPostRequestIsMade_thenReturnSuccess() throws Exception {

        String request = """
                {
                    "groupNumber":  1,
                    "name": "Reactive Nonmetals",
                    "description": "Reactive nonmetals"
                }
                """;

        String response = """
                {
                    "id": 2,
                    "groupNumber": 1,
                    "name": "Reactive Nonmetals",
                    "description": "Reactive nonmetals"
                }
                """;

        mockMvc.perform(post("/elementGroups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void givenValidGroup_whenGetRequestIsMade_thenReturnSuccess() throws Exception {

        String jsonreponse =
                """
                        [
                        
                         {
                            "id": 1,
                           "groupNumber": 1,
                           "name": "Reactive Nonmetals",
                           "description": "Reactive nonmetals tend to gain electrons."
                          }
                        ]
                        """;

        mockMvc.perform(get("/elementGroups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonreponse));

    }

    @Test
    void givenValidIdGroup_whenGetRequestIsMade_thenReturnSuccess() throws Exception {

        String jsonresponse =
                """
                        
                          {
                            "id": 1,
                           "groupNumber": 1,
                           "name": "Reactive Nonmetals",
                           "description": "Reactive nonmetals tend to gain electrons."
                          }
                        
                        """;


        mockMvc.perform(get("/elementGroups/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));
    }

    @Test
    void givenGroupById_whenDelete_theReturnSuccess() throws Exception {


        mockMvc.perform(delete("/elementGroups/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(0, elementGroupRepository.count());
    }

    @Test
    void givenGroupById_whenUpdate_thenReturnSuccess() throws Exception {

        String jsonrequest =
                """
                        
                          {
                            "id": 1,
                           "groupNumber": 1,
                           "name": "Reactive Nonmetals",
                           "description": "Reactive nonmetals tend to gain electrons."
                          }
                        
                        """;

        String jsonresponse =
                """
                          {
                            "id": 1,
                           "groupNumber": 1,
                           "name": "Reactive Nonmetals",
                           "description": "Reactive nonmetals tend to gain electrons."
                          }
                        """;

        mockMvc.perform(put("/elementGroups/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));

    }
}
