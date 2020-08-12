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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.junit.Assert.*;
/**
 *
 * @author hasee
 */
public class MoveNotFoundTest {

    public static void main(String [] args) throws Exception {
        testMove();
    }

    private static void testMove() throws URISyntaxException, IOException {
        //Make a move without initialise session which 
        //should result in a 404 game not found error
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/move/x1y1")
                .build();
        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            // Make sure status code recieved is equal to 404 Not Found
            assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());
        }
    }
}
