package com.nasrinsohrabi.readinglist.clients.models;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Books {

    private String title;
    private String description;
  //  private LocalDate publishedDate;

    public void setTitle(String title){ this.title = title;}
    public String getTitle(){return title;}

    public void setDescription(String description){ this.description = description;}
    public String getDescription(){return description;}

  //  public void setPublishedDate(LocalDate publishedDate){ this.publishedDate = publishedDate;}
  //  public LocalDate getPublishedDate(){return publishedDate;}


}
