package com.nasrinsohrabi.readinglist.repositories;

import com.nasrinsohrabi.readinglist.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Created by Nasrin on 18/09/2017.
 */
public interface ReaderRepository extends JpaRepository<Reader, String>{

   Optional<Reader> findByFirstname(String username);
}
