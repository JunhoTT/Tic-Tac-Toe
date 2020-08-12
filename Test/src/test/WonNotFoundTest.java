/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.net.URI;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.junit.Assert.*;
/**
 *
 * @author hasee
 */
public class WonNotFoundTest {

    public static void main(String [] args) throws Exception {
        // Send won request without initialisie the game session
        testWin();
    }
    
    private static void testWin() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/won")
                .build();
        HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            // Make sure that the status code recieved is equal to 404 Not Found
            assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());
        } 
    }
}
