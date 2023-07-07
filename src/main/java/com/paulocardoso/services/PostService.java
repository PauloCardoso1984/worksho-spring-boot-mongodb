package com.paulocardoso.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulocardoso.domain.Post;
import com.paulocardoso.repository.PostRepository;
import com.paulocardoso.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado - ERRO 0001"));
	}
	
	// BUSCAR POSTS CONTENDO UM DADO STRING
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	// BUSCAR POSTS CONTENDO QUALDQER DADO DO POST
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
