package com.modiranedaneshmehvar.springbootproject.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nasrin on 15/07/2017.
 */
public interface
ReadingListRepository extends JpaRepository<Book, Long> {

  List<Book> findByReader(String reader);
}
