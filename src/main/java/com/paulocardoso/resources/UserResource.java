package com.paulocardoso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulocardoso.domain.Post;
import com.paulocardoso.domain.User;
import com.paulocardoso.dto.UserDTO;
import com.paulocardoso.services.UserService;

// RECURSO REST COM ANOTAÇÃO
@RestController

// CAMINHO DO ENDPOINT
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service; 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		
		// TRANSFORMAR LISTA USER EM LISTA DTO - EXPRESSÃO LÂMBDA
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}
	
	
	//METODO PARA RETORNAR O USURIO POR ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	
	//METODO PARA DELETAR O USURIO POR ID - CODIGO 204
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<UserDTO> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// INSERIR UM NOVO USUÁRIO
	@RequestMapping(method=RequestMethod.POST)
	// OU PODE USAR A ANOTATION →    @PostMapping
 	public ResponseEntity<Void> insert(@RequestBody UserDTO ObjDto) {
		User obj = service.fromDTO(ObjDto);
		obj = service.insert(obj);
		
		// RESPOSTA VAZIA COM CABEÇALHO DI RECURSO CRIADO - CODIGO 201.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

	// UPDATE EM UM USUÁRIO
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody UserDTO ObjDto, @PathVariable String id) {
		User obj = service.fromDTO(ObjDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		}
	
	//METODO PARA RETORNAR POSTS
	@RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
