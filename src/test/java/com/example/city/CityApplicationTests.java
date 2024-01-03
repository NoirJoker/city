package com.example.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CityApplicationTests {

  @Autowired
  private Controller controller;

  @Test
  void testMe() {
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    ResponseEntity<String> response = testRestTemplate.
        getForEntity("http://localhost:8080/cities", String.class);

    System.out.println(response.getStatusCode());
    System.out.println(response.getBody());

  }

  @Test
  void test() {
    System.out.println(controller.listCities());
  }

}
