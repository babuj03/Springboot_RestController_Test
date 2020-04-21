package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.ContactNotFoundException;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	
	@GetMapping
	public String ping() {
		return "Server is up";
	}
	
	@GetMapping(value = "/get/{id}", produces = "application/json")
    public ContactEntity getContact(@PathVariable(name = "id") long id) throws ContactNotFoundException  {
		ContactEntity res = contactService.findById(id).orElse(null);
		if(res == null)
		   throw new ContactNotFoundException("Contact Not Found");
		return res;
	}
	
	@GetMapping(value = "/all",  produces = "application/json")
	public List<ContactEntity> getContacts() {
		return contactService.findAll();
	}
	
	
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactEntity saveContacts(@RequestBody ContactEntity contact) {
		return contactService.save(contact);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteContacts(@PathVariable(name = "id") long id) {
		contactService.delete(id);
		
	}
	

}
