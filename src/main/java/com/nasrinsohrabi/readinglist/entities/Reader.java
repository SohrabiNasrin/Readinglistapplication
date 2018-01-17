package com.nasrinsohrabi.readinglist.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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

public class Reader implements  UserDetails{

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "email", nullable = false , unique = true)
    @Email(message = "Please provide a valid e-mail ")
    @NotEmpty(message = "Please provide an E-mail")
    private String email;

    @OneToMany
    private Set<Book> books = new HashSet<>();


    @Column(name = "lastname")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @Column(name = "firstname")
    @NotEmpty(message = "Please provide your fist name")
    private String firstName;


    @Column(name = "confirmationtoken")
    private String confirmationToken;

    @Column(name = "password")
    @Transient
    private String password;


    private boolean enable;

   /******** Constructors ***************/

   public Reader(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    Reader(){}


    /**************** methods **************/

    public Set<Book> getBooks() {return books;}

    public void setEnabled(Boolean enable){ this.enable = enable;}
    public Boolean isEnable(){return this.enable;}

    public Long getUserid() {return userid; }
    public void setUserid(Long userId) {
        this.userid = userid;
    }

    public String getConfirmationToken(){
        return confirmationToken;
    }
    public void setConfirmationToken(String confirmationToken){
        this.confirmationToken = confirmationToken;
    }

    public String getEmail(){return email;}
    public void setEmail(String email){ this.email = email;}


    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
