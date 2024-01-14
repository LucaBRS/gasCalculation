package com.example.gascalcapp.dao;


import com.example.gascalcapp.model.SearchRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CarburantiControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(CarburantiControllerTest.class);

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void testSearchArea() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setRegion(1);
        searchRequest.setProvince(null);
        searchRequest.setTown(null);

        // Convert the object to JSON
        String requestJson = "{\"region\":1,\"province\":null,\"town\":null}";

        //TODO
//        // Perform the request and assert the response
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/carburanti/search/area")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                // Add more assertions based on your expected JSON response
//                // For example, if you expect a certain field in the response
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldName").value("expectedValue"));

        // Make the request and verify the response
        webTestClient.post()
                .uri("/api/carburanti/search/area")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(searchRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String responseBody = new String(response.getResponseBodyContent());
                    System.out.println("Response JSON: " + responseBody);

                    // Add more assertions based on your expected JSON response
                });
    }
}