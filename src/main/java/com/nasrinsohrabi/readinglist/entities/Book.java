package com.nasrinsohrabi.readinglist.entities;

/**
 * Created by Nasrin on 7/07/2017.
 */

import javax.persistence.*;

/**
 * Created by Nasrin on 7/07/2017.
 */

@Entity
@Table (name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @ManyToOne
  //  private Reader readerentity;
    private Long   userid;

    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;



  /************Constructors*********************/

   public Book(Reader readerentity, String reader){
        this.reader = reader;
       // this.readerentity = readerentity;
    }

   Book(){}

    /**************** methods*****************/

  //  public Reader getReaderentity() {return readerentity;}


    public Long getUserid(){return userid;}
    public void setUserid(Long userId) {this.userid = userid;}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getReader() {
        return reader;
    }
    public void setReader(String reader) {
        this.reader = reader;
    }



    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}