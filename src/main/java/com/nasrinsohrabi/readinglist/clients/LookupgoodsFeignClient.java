package com.nasrinsohrabi.readinglist.clients;

import com.nasrinsohrabi.readinglist.clients.models.Books;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient ("LookupgoodsService")
public interface LookupgoodsFeignClient {

    @RequestMapping (
            method = RequestMethod.GET,
            value = "/itemsearch/books/{description}",
            consumes = "application/json")

    Books getBooks(@PathVariable ("description") String description);
}
