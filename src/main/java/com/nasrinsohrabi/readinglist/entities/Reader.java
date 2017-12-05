package com.nasrinsohrabi.readinglist.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nasrin on 18/09/2017.
 */
@Entity

public class Reader implements UserDetails {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @OneToMany
    private Set<Book> books = new HashSet<>();

    private String username;
    private String lastname;
    private String firstname;
    private String password;

   /********Constructors***************/

   public Reader(String username, String lastname){
        this.username = username;
        this.lastname = lastname;
    }

    Reader(){}


    /**************** methods*****************/

    public Set<Book> getBooks() {return books;}

    public Long getUserid() {return userid; }
    public void setUserid(Long userId) {
        this.userid = userid;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstname(){
        return username;
    }
    public void setFirstname(String username){
        this.username = username;
    }


    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("READER"));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
