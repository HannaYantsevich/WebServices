package tests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import post.User;

public class RestTemplTest {
    @Test
    public void checkStatusCode() {
        RestTemplate restTemple = new RestTemplate();
        ResponseEntity<User[]> response = restTemple.getForEntity("http://jsonplaceholder.typicode.com/posts", User[].class);
        int actStatusCode = response.getStatusCode().value();
        Assert.assertEquals(actStatusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTemple = new RestTemplate();
        ResponseEntity<User[]> response = restTemple.getForEntity("http://jsonplaceholder.typicode.com/posts", User[].class);
        HttpHeaders headers = response.getHeaders();

        String actContentTypeValue = headers.getContentType().toString();
        Assert.assertEquals(actContentTypeValue, "application/json;charset=utf-8");
    }

    @Test()
    public void checkResponseBody() {
        RestTemplate restTemple = new RestTemplate();
        ResponseEntity<User[]> response = restTemple.getForEntity("http://jsonplaceholder.typicode.com/posts", User[].class);
        User[] actUsers = response.getBody();
        Assert.assertEquals(actUsers.length, 100);
    }
}
