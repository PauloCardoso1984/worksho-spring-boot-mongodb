package com.paulocardoso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.paulocardoso.domain.User;
import com.paulocardoso.services.UserService;

// RECURSO REST COM ANOTAÇÃO
@RestController

// CAMINHO DO ENDPOINT
@RequestMapping(value= "/users")
public class UserResource {

	@Autowired
	private UserService service; 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
