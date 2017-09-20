package com.modiranedaneshmehvar.springbootproject.readinglist;

/**
 * Created by Nasrin on 7/07/2017.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Nasrin on 7/07/2017.
 */

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;


    //Id methods

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Reader methods
    public String getReader() {
        return reader;
    }
    public void setReader(String reader) {
        this.reader = reader;
    }

    // isbn methods

    public String getisbn() {
        return isbn;
    }
    public void setisbn(String isbn) {
        this.isbn = isbn;
    }

    // title method
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // author methods
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    // description methods
    public String getDescroption() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}