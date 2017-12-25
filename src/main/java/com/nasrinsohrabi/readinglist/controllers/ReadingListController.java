package com.nasrinsohrabi.readinglist.controllers;

import com.nasrinsohrabi.readinglist.entities.Book;
import com.nasrinsohrabi.readinglist.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Nasrin on 15/07/2017.
 */

@Controller
@RequestMapping("/List")

public class ReadingListController {


    private ReadingListRepository readingListRepository;

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
         return "readingList";
    }

    @RequestMapping(value="/{reader}" , method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader , Book book){
        book.setReader(reader);
        System.out.println(" Reader has been set .." + book.getReader()
                + " Reader Id : " + book.getId() );
        readingListRepository.save(book);

        return "redirect:/List/{reader}";
    }
}