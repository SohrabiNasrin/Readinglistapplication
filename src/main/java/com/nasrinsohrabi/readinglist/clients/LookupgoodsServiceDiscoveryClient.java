package com.nasrinsohrabi.readinglist.clients;


import com.nasrinsohrabi.readinglist.clients.models.Books;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LookupgoodsServiceDiscoveryClient {

 /*   @Autowired
    DiscoveryClient discoveryClient;

    public Books getBooks(String description){
      RestTemplate restTemplate = new RestTemplate();
      List<ServiceInstance> instances =
              discoveryClient.getInstances("LookupgoodsService");

      if(instances.size() == 0) return null;

      String serviceUri = String.format("%s/itemsearch/books/{description}%s", instances.get(0).getUri().toString(), description);

        ResponseEntity<Books> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                Books.class(),
                description);

        return restExchange.getBody();


    }
 */
}
