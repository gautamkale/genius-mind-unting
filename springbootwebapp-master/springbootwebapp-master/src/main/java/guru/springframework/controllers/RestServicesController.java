package guru.springframework.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.domain.Person;
import guru.springframework.services.LinkedinScrapper;

@RestController
public class RestServicesController {

	@Autowired
	private LinkedinScrapper scrapper;
	@GetMapping("/linkedinids")
	public @ResponseBody List<Person> retrieveNames(@RequestParam  String fileName) {
		List<Person> ls=scrapper.Login(fileName);
		return ls;
		
	}
}
