package com.nasrinsohrabi.readinglist.controllers;

import com.nasrinsohrabi.readinglist.entities.Book;
import com.nasrinsohrabi.readinglist.exceptionHandling.UserNotFoundException;
import com.nasrinsohrabi.readinglist.repositories.ReaderRepository;
import com.nasrinsohrabi.readinglist.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Nasrin on 3/12/2017.
 */

@RestController
@RequestMapping("/readingListrestservice/{reader}")
public class ReadingListRestController {

    private final ReadingListRepository readingListRepository ;
    private final ReaderRepository readerRepository;

    @Autowired
    public ReadingListRestController (ReadingListRepository readingListRepository
                                      , ReaderRepository readerRepository){
        this.readingListRepository = readingListRepository ;
        this.readerRepository = readerRepository ;
    }

    @RequestMapping(value = "/{description}" , method = RequestMethod.GET)
    public Collection<Book> getComputerScienceReadingList(@PathVariable String reader ,
                                            @PathVariable("description") String description){
          validateUser(reader);

          return readingListRepository.findByReader(reader).stream()
                 .filter(book -> book.getDescription().trim().equalsIgnoreCase(description.trim()))
                 .collect(Collectors.toList());

    }

    @RequestMapping (method = RequestMethod.POST)
    ResponseEntity<?>  add(@PathVariable String reader, @RequestBody Book input ){
        validateUser(reader);
        input.setReader(reader);
        Book result = readingListRepository.save(input);
        System.out.println(" New book: ' " + input.getTitle() + " '  will be added to " +
               reader + "'s readinglist");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{reader}")
            .buildAndExpand(result.getReader()).toUri();
        return ResponseEntity.created(location).build();
    }


    private void validateUser(String userName) {
        readerRepository.findByFirstname(userName)
                 .orElseThrow( () -> new UserNotFoundException(userName) );
         }
}
