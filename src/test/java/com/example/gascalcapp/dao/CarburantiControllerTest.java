package com.example.gascalcapp.dao;


import com.example.gascalcapp.model.SearchRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarburantiControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(CarburantiControllerTest.class);

//    @Autowired
//    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Test
    public void testSearchArea() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJsonNode  = null;

        Map<Integer,JsonNode> resultMap =new HashMap<>();

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Create the request headers
        HttpHeaders headers = new HttpHeaders();

        // Define the URL and request body
        String url = "https://carburanti.mise.gov.it/ospzApi/search/area";
        String requestBody = "{\"region\": 1, \"province\": null, \"town\": null}";

/*        TODO
        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/carburanti/search/area")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                // Add more assertions based on your expected JSON response
                // For example, if you expect a certain field in the response
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldName").value("expectedValue"));
*/

        //Set the request headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);


        // Make the POST request and get the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);


        // Print the entire response
        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        responseJsonNode = objectMapper.readTree(responseEntity.getBody()).get("results");

        for(int i=0;i<responseJsonNode.size();i++){
            JsonNode elemNode = responseJsonNode.get(i);
            int id = elemNode.get("id").asInt();
            ((ObjectNode) elemNode).remove("id");
            JsonNode restElem = elemNode;
            resultMap.put(id,restElem);
        }

        LOG.info("Status Code: " + statusCode);
        LOG.info("Response Headers: " + responseHeaders);
        LOG.info("Response Body: " + resultMap);
    }
}