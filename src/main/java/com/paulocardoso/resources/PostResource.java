package com.paulocardoso.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paulocardoso.domain.Post;
import com.paulocardoso.resources.util.URL;
import com.paulocardoso.services.PostService;

// RECURSO REST COM ANOTAÇÃO
@RestController

// CAMINHO DO ENDPOINT
@RequestMapping(value="/posts")
public class PostResource {
 
	@Autowired
	private PostService service; 
	
	//METODO PARA RETORNAR O USURIO POR ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// BUSCA PELA URL
	@RequestMapping(value="/{id}/titlesearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> findByIdTitle(@RequestParam(value="text", defaultValue= "") String text) {
		text = URL.decodeParak(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	// BUSCA POR QUALQUER PALAVRA DO POST
	@RequestMapping(value="/{id}/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue= "") String text,
 			@RequestParam(value="minDate", defaultValue= "") String minDate,
 			@RequestParam(value="maxDate", defaultValue= "") String maxDate) {
		text = URL.decodeParak(text);
		Date min = URL.convertDate(minDate,  new Date(0L));
		Date max = URL.convertDate(maxDate,  new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}

