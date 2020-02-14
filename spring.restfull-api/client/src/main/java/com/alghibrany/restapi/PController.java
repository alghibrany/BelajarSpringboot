package com.alghibrany.restapi;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ahmad
 */
@RestController
public class PController {
   @Autowired
   RestTemplate restTemplate;

   //get all
   @RequestMapping(value = "/")
   public String getProductList() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity <String> entity = new HttpEntity<>(headers);
      
      return restTemplate.exchange("http://localhost:8080/persons/all", 
              HttpMethod.GET, entity, String.class).getBody();
   }
   
      //get by id
   @RequestMapping(value = "/{id}")
   public String getProductList(@PathVariable String id) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity <String> entity = new HttpEntity<>(headers);
      
      return restTemplate.exchange("http://localhost:8080/persons/"+id,  
              HttpMethod.GET, entity, String.class).getBody();
   }
     //untuk edit
   @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
   public String updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Person> entity = new HttpEntity<Person>(person,headers);
      
      return restTemplate.exchange(
         "http://localhost:8080/persons/"+id, HttpMethod.PUT, entity, String.class).getBody();
      
   }
   
   //add
   @RequestMapping(value = "/template/persons", method = RequestMethod.POST)
   public String addPerson(@RequestBody Person person) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Person> entity = new HttpEntity<Person>(person,headers);
      
      
      return restTemplate.exchange(
         "http://localhost:8080/persons", HttpMethod.POST, entity, String.class).getBody();
   }
   
   @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
   public String deletePerson(@PathVariable("id") String id) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Person> entity = new HttpEntity<Person>(headers);
      
      return restTemplate.exchange(
         "http://localhost:8080/persons/"+id, HttpMethod.DELETE, entity, String.class).getBody();
   }
   
}
