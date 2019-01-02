package com.scrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {

    @Autowired
  //  LoginService service;

 /*   @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }*/

    @RequestMapping("/")
    String index(){
        return "index";
    }
    @RequestMapping(value="/emailvalidate", method = RequestMethod.GET)
    public String showWelcomePage(){

       /* boolean isValidUser = service.validateUser(name, password);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
*/
       // model.put("name", name);
     //  .model.model.put("password", password);

        return "emailvalidate";
    }

}
