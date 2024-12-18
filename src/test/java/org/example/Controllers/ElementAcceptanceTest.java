package org.example.Controllers;

import org.example.entities.Element;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ElementAcceptanceTest {
    @Autowired
    private ElementRepository elementRepository;
    @Autowired
    private ElementGroupRepository elementGroupRepository;


    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ElementGroup group1 = new ElementGroup(1, "Reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        elementGroupRepository.save(group1);

        Element element = new Element("Fluorine", 9, "F", LocalDate.of(1886, 01,01), "Marie Curie, Irène Joliot-Curie", group1);
        elementRepository.save(element);
    }

    @Test
    void givenAValidElement_whenPostRequestIsMade_thenReturnSuccess() throws Exception {

        String request = """
                 {
                  "name": "Oxygen",
                  "atomicNumber": 8,
                  "symbol": "O",
                  "discoveryDate": "1774-01-01",
                  "discoveredBy": "Joseph Priestley",
                  "groupId": 1
                }
                """;

        String response = """
                {
                   "id" : 2,
                  "name": "Oxygen",
                  "atomicNumber": 8,
                  "symbol": "O",
                  "discoveryDate": "1774-01-01",
                  "discoveredBy": "Joseph Priestley",
                  "elementGroup": {
                    "id": 1,
                    "name": "Reactive Nonmetals",
                    "description": "Reactive nonmetals tend to gain electrons."
                  }
                }
                """;

        mockMvc.perform(post("/elements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }



    @Test
    void givenElementById_whenDelete_theReturnSuccess() throws Exception {


        mockMvc.perform(delete("/elements/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(0, elementRepository.count());
    }

    @Test
    void givenElementById_whenUpdate_thenReturnSuccess() throws Exception {

        String jsonrequest =
                """
                        
                         {
                            "name": "Fluorine",
                            "atomicNumber": 9,
                            "symbol": "F",
                            "discoveryDate": "1886-01-01",
                            "discoveredBy": "Marie Curie",
                            "groupId": 1
                        }
                        
                        """;

        String jsonresponse =
                """
                            {
                             "id": 1,
                             "name": "Fluorine",
                             "atomicNumber": 9,
                             "symbol": "F",
                             "discoveryDate": "1886-01-01",
                             "discoveredBy": "Marie Curie",
                             "elementGroup": {
                                "id": 1,
                                "groupNumber": 1,
                                "name": "Reactive Nonmetals",
                                "description": "Reactive nonmetals tend to gain electrons."
                            }
                         }
                        """;

        mockMvc.perform(put("/elements/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonrequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonresponse));

    }

}
