package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exception.ContactNotFoundException;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository repo;
	
	public Optional<ContactEntity> findById(long id) throws ContactNotFoundException {
		return repo.findById(id);
	}
	
	public List<ContactEntity> findAll(){
		return repo.findAll();
	}

	public void delete(long id){
	   repo.deleteById(id);
	}
	
	public ContactEntity save(ContactEntity contact){
		return  repo.save(contact);
	}
}
