package com.modiranedaneshmehvar.springbootproject.readinglist.repositories;

import com.modiranedaneshmehvar.springbootproject.readinglist.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Created by Nasrin on 18/09/2017.
 */
public interface ReaderRepository extends JpaRepository<Reader, String>{

}
