package com.nasrinsohrabi.readinglist.controllers;

import com.nasrinsohrabi.readinglist.clients.LookupgoodsFeignClient;
import com.nasrinsohrabi.readinglist.clients.LookupgoodsRestTemplateClient;
import com.nasrinsohrabi.readinglist.clients.models.Books;
import com.nasrinsohrabi.readinglist.entities.Book;
import com.nasrinsohrabi.readinglist.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nasrin on 15/07/2017.
 */

@Controller
@RequestMapping("/home")

public class ReadingListController {


    private ReadingListRepository readingListRepository;

    @Autowired
    LookupgoodsFeignClient lookupgoodsFeignClient;

    @Autowired
    LookupgoodsRestTemplateClient lookupgoodsRestTemplateClient;


    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;

    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readerBooks(
        @PathVariable("reader") String reader, Model model) {
            List<Book> readingList = readingListRepository.findByReader(reader);
          if (readingList != null){
                model.addAttribute("books" , readingList);
        }
         return "home";
    }

    @RequestMapping(value = "itemsearch/{reader}/{description}", method = RequestMethod.GET)
    public String findBookFromLookupgoodsService(@PathVariable ("reader") String reader , @PathVariable ("description" ) String description , Model model) {

        System.out.println("reader is : " + reader + "  description is: " + description);
        List<Books> books1 = lookupgoodsRestTemplateClient.getLookupgoodsService(description).stream()
                .filter(books -> books.getDescription().equalsIgnoreCase(description))
                .collect(Collectors.toList());
        model.addAttribute("books" , books1.get(0));

// Books books1 =  lookupgoodsFeignClient.getBooks(description);

       return "lookupservice";

    }

    @RequestMapping(value="/{reader}" , method = RequestMethod.POST)
      public String addToReadingList(@PathVariable("reader") String reader , Book book){
        book.setReader(reader);
        System.out.println(" Reader has been set .." + book.getReader()
                + " Reader Id : " + book.getId() );
        readingListRepository.save(book);

        return "redirect:/home/{reader}";
    }
}
