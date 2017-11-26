package com.nasrinsohrabi.springbootproject.readinglist;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Nasrin on 12/10/2017.
 */
public class ReadingListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){

        return builder.sources(ReadingListApplication.class);
     }
}
