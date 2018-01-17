package com.nasrinsohrabi.readinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Nasrin on 4/11/2017.
 */
@Controller
@RequestMapping("/")
public class ReadingListHomePageController {

    @RequestMapping("/")
    public String getHomePage(){
        return "readinglistmainpage";
    }
    @RequestMapping("/home")
    public String getReadeList(){
        return  "redirect:/home";
    }


}
