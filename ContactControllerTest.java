package com.spring.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ContactControllerTest {

   @Autowired
   MockMvc mockmvc;
   
   @Test
	public void ContactNotFoundException() throws Exception {
	   mockmvc.perform( get("/api/contacts/get/1111").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			   .accept(MediaType.APPLICATION_JSON))
	   .andDo(print())
	   .andExpect(jsonPath("$.message", is("Contact Not Found")));
	}
	
   @Test
	public void findContactById() throws Exception {
		//RequestBuilder
		  mockmvc.perform( get("/api/contacts/get/111") 
		  .accept(MediaType.APPLICATION_JSON) )
		 .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.id", is(111)));
	}
	
	@Test
	private void deleteContactById() throws Exception {
		mockmvc.perform(delete("/api/contacts/delete/111")).andExpect(status().isOk());
	}
	
	@Test
	
	public void saveContact() throws Exception {
		
		ContactEntity ent = new ContactEntity();
		ent.setId(12);
		ent.setFirstName("babu");
		ent.setLastName("Jayaraman");
		ent.setPhone("2345678");
		ent.setPostCode("NE65BJ");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String content= mapper.writeValueAsString(ent);
		ResultActions action = mockmvc.perform(post("/api/contacts/save").contentType(MediaType.APPLICATION_JSON).content(content))
				 .andDo(print())
				.andExpect(status().isOk());
		
	}
	
	
	

}
