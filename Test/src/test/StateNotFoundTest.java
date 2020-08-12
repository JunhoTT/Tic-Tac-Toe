/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author hasee
 */
public class StateNotFoundTest {

    public static void main(String [] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // Request state without initialisie game session
        testStatePng(httpclient);
        testStateTxt(httpclient);
    }
    
    private static void testStatePng(CloseableHttpClient httpclient) throws URISyntaxException, IOException {
        URI uri3 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/state")
                .setParameter("format", "png")
                .build();
        HttpGet httpGet3 = new HttpGet(uri3);
        try (CloseableHttpResponse response3 = httpclient.execute(httpGet3)) {
            //Make sure status code recieved is equal to 404 Not Found
            assertEquals(HttpStatus.SC_NOT_FOUND, response3.getStatusLine().getStatusCode());
        }
    }

    private static void testStateTxt(CloseableHttpClient httpclient) throws URISyntaxException, IOException {
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/state")
                .setParameter("format", "txt")
                .build();
        HttpGet httpGet = new HttpGet(uri2);
        try (CloseableHttpResponse response2 = httpclient.execute(httpGet)) {
            //Make sure status code recieved is equal to 404 Not Found
            assertEquals(HttpStatus.SC_NOT_FOUND, response2.getStatusLine().getStatusCode());
        }
    }
}
