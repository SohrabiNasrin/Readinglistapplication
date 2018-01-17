package com.nasrinsohrabi.readinglist.services;


import com.nasrinsohrabi.readinglist.entities.Reader;
import com.nasrinsohrabi.readinglist.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    private ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository){
        this.readerRepository = readerRepository;
    }

    public Reader findByEmail(String email){
        return readerRepository.findByEmail(email);
    }

    public Reader findByConfirmationToken(String confirmationToken){
        return readerRepository.findByConfirmationToken(confirmationToken);
    }

    public void saveReader(Reader reader){
        readerRepository.save(reader);
    }
}
