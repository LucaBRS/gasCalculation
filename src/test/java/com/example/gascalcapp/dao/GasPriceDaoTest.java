package com.example.gascalcapp.dao;

import com.example.gascalcapp.model.GasPrice;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = {
        "gas.price.api.url=http://example.com/api/gasprices"
})
public class GasPriceDaoTest {
    @Autowired
    private GasPriceDao gasPriceDao;

    @MockBean
    private HttpClient httpClient;

    @BeforeEach
    public void setup() {
        when(httpClient.execute(any(HttpGet.class))).thenReturn(createHttpResponse(200, "[{\"type\":\"Euro95\",\"price\":1.5},{\"type\":\"Diesel\",\"price\":1.3}]"));
    }

    @Test
    public void testGetAllGasPrices() {
        List<GasPrice> gasPrices = gasPriceDao.getAllGasPrices();
        assertEquals(2, gasPrices.size());

        GasPrice gasPrice1 = gasPrices.get(0);
        assertEquals("Euro95", gasPrice1.getGasType());
        assertEquals(1.5, gasPrice1.getPrice());

        GasPrice gasPrice2 = gasPrices.get(1);
        assertEquals("Diesel", gasPrice2.getGasType());
        assertEquals(1.3, gasPrice2.getPrice());
    }

    @Test
    public void testGetGasPriceByType() {
        GasPrice gasPrice = gasPriceDao.getGasPriceByType("Euro95");
        assertEquals("Euro95", gasPrice.getGasType());
        assertEquals(1.5, gasPrice.getPrice());
    }

    private HttpResponse createHttpResponse(int statusCode, String responseContent) {
        StatusLine statusLine = new BasicStatusLine(new org.apache.http.protocol.HTTP(), statusCode, "");
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        HttpEntity httpEntity = Mockito.mock(HttpEntity.class);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        try {
            InputStream inputStream = new ByteArrayInputStream(responseContent.getBytes(StandardCharsets.UTF_8));
            when(httpEntity.getContent()).thenReturn(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}