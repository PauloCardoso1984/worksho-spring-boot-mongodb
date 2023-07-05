package com.paulocardoso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulocardoso.domain.User;
import com.paulocardoso.dto.UserDTO;
import com.paulocardoso.repository.UserRepository;
import com.paulocardoso.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado - ERRO 0001"));
	}
	
	// INSERIR UM OBJETO
	public User insert (User obj) {
		return repo.insert(obj);
	}
	
	// DELETAR UM OBJETO
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	// PEGAR O DTO E INSTANCIAR O USUARIO
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
	
}
