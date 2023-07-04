package com.paulocardoso.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paulocardoso.domain.User;

// RECURSO REST COM ANOTAÇÃO
@RestController

// CAMINHO DO ENDPOINT
@RequestMapping (value= "/users")
public class UserResource {

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity< List<User>> findAll() {
		User davi = new User("1", "Davi", "davi@maria");
		User paulo = new User("2", "Paulo", "paulo@gmail");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(davi, paulo));
		return ResponseEntity.ok().body(list);
	}
	
}
