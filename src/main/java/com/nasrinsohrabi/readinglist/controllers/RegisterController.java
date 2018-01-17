package com.nasrinsohrabi.readinglist.controllers;


import com.nasrinsohrabi.readinglist.entities.Reader;
import com.nasrinsohrabi.readinglist.services.EmailService;
import com.nasrinsohrabi.readinglist.services.ReaderService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ReaderService readerService;
    private EmailService  emailService;


    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
                              ReaderService readerService, EmailService emailService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.readerService = readerService;

    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, Reader reader){
        modelAndView.addObject("reader", reader);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registrationForm(ModelAndView modelAndView, @Valid Reader reader, BindingResult bindingResult, HttpServletRequest httpServletRequest){


      try {

          Reader newReader = readerService.findByEmail(reader.getEmail());

          if (newReader != null) {
              modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
              modelAndView.setViewName("register");
              bindingResult.reject("email");
          }

          if (bindingResult.hasErrors()) {
              modelAndView.setViewName("register");
          } else {
              System.out.println("inside try : " +  reader.getEmail());

              reader.setEnabled(false);  // disable reader until they click on the confirmation link

              // Generate random 36-character string token for confirmation link
              reader.setConfirmationToken(UUID.randomUUID().toString());

              String appUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName();

              SimpleMailMessage registrationEmail = new SimpleMailMessage();
              registrationEmail.setTo(reader.getEmail());
              registrationEmail.setSubject("Registration Confirmation");
              registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                      + appUrl + "/confirm?token=" + reader.getConfirmationToken());
            //  registrationEmail.setFrom("noreply@domain.com");

              emailService.sendEmail(registrationEmail);

              readerService.saveReader(reader);

              modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + reader.getEmail());
              modelAndView.setViewName("register");

          }

      }catch(Exception e){System.out.println( "Exception: " + e);}
        return modelAndView;
    }

    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {

        Reader reader = readerService.findByConfirmationToken(token);

        if (reader == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", reader.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        modelAndView.setViewName("confirm");

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure(requestParams.get("password"));

        if (strength.getScore() < 4) {
            modelAndView.addObject("errorMessage", "Your password is too weak.  Choose a stronger one.");
            bindingResult.reject("password");
            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        // Find the user associated with the reset token
        Reader reader = readerService.findByConfirmationToken(requestParams.get("token"));

        // Set new password
        reader.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

        // Set user to enabled
        reader.setEnabled(true);

        // Save user
        readerService.saveReader(reader);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }






}
