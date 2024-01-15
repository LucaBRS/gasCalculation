package com.example.gascalcapp.dao;


import com.example.gascalcapp.model.SearchRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


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
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.setRegion(1);
//        searchRequest.setProvince(null);
//        searchRequest.setTown(null);

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
        String responseBody = responseEntity.getBody();

        LOG.info("Status Code: " + statusCode);
        LOG.info("Response Headers: " + responseHeaders);
        LOG.info("Response Body: " + responseBody);
    }
}