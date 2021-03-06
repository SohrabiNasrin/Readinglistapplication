package com.nasrinsohrabi.readinglist.securities;

import com.nasrinsohrabi.readinglist.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Nasrin on 18/09/2017.
 */
@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
    private ReaderRepository readerRepository;

        @Override
    protected void configure(HttpSecurity http) throws Exception{

            http.authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/confirm").permitAll();


            http.
            authorizeRequests()
               .antMatchers("/").access
               ("hasRole ('READER')")
               .antMatchers("/**").permitAll()
               .and()

               .formLogin()
                  .loginPage("/login")
                  .failureUrl("/loging?error= true");

       }

     @Override
     protected void configure(
             AuthenticationManagerBuilder auth) throws Exception {
             auth.userDetailsService(new UserDetailsService() {
                 @Override
                 public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
                     return readerRepository.findOne(firstName);
                 }
             });
     }
}
