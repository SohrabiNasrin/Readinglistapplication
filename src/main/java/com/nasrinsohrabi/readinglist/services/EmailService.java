package com.nasrinsohrabi.readinglist.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.mail;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        Properties javaMailServiceProperties = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        javaMailServiceProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailServiceProperties.setProperty("mail.smtp.auth" , "true");
        javaMailServiceProperties.setProperty("mail.debug", "debug");

        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) mailSender).setPort(587);
        ((JavaMailSenderImpl) mailSender).setUsername("khodakhodaieyan@gmail.com");
        ((JavaMailSenderImpl) mailSender).setPassword("MilaD061");
        ((JavaMailSenderImpl) mailSender).setProtocol("smtp");
        ((JavaMailSenderImpl) mailSender).setJavaMailProperties(javaMailServiceProperties);
        System.out.println("mailService properties: " + ((JavaMailSenderImpl) mailSender).getJavaMailProperties());
        mailSender.send(email);
    }

}
