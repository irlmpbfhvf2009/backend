package com.lwdevelop.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {
	
    @LocalServerPort
    private int port;

	private String baseUrl;
    private TestRestTemplate restTemplate;
	
    @BeforeEach
    public void setUp(){
        this.baseUrl="http://127.0.0.1:"+port;
        this.restTemplate = new TestRestTemplate();
    }

	@Test
	void contextLoads() {
	}

	@Test
    public void index() {
        System.out.println("request url is: "+baseUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getBody(),"Welcome to Spring Boot!");
    }

}
