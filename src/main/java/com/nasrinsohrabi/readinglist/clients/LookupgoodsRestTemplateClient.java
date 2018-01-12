package com.nasrinsohrabi.readinglist.clients;

import com.nasrinsohrabi.readinglist.clients.models.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class LookupgoodsRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public Collection<Books> getLookupgoodsService(String description){

        System.out.println("Inside getLookupsgoodsService method form LookupgoodsRestTemplateClient...");
       try {

           System.out.println("The RestTemplate is : " + restTemplate.getUriTemplateHandler().toString());


           ResponseEntity<Books[]> restExchange = restTemplate.exchange(
                   "http://LookupgoodsService/itemsearch/books/{description}",
                   HttpMethod.GET,
                   null, Books[].class, description);

           System.out.println("URL address: " + restTemplate.getUriTemplateHandler());
           Books[] booksList = restExchange.getBody();
           List<Books> booksList1 = Arrays.asList(booksList);
           return booksList1;
    }catch(Exception e) {System.out.println("Inside getLookupgoodsService method exception: " + e); return null;}


}


}
