package br.com.brunodemartini.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brunodemartini.course.entities.User;
import br.com.brunodemartini.course.repositories.UserRepository;
import br.com.brunodemartini.course.services.UserService;

//@RestController                 --> Indica que a classe é um recurso web que implementada por um controlador Rest
//@RequestMapping(value="/users") --> Indica o caminho para acessar o recurso

/* Logo, a Classe User Resource é a classe Controller, que fará a interface entre a aplicação 
 *       e a camada Service Layer, ou seja, de regra de negócio, que nesse caso é a UserService.
 */

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired //Diz que a classe UserRespository é uma classe de injeção de dependência
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	//@GetMapping    --> Indica que é um  método que responde a uma chammada get() do http.
	//ResponseEntity --> É um tipo padrão de retorno de recursos web para um recurso.
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> listUsers  = userRepository.findAll();
		
		//.OK() --> indica uma resposta com sucesso da web.
		//.body --> indica o corpo da resposta.
		return ResponseEntity.ok().body(listUsers);
	}
	
	// Marcação do GetMapping que define que o atributo id será passado por parâmetro na URL pelo GET()
	@GetMapping(value = "/{id}") 
	//@PathVariable --> Indica que o parâmetro recebido (id) é o valor que virá pelo get() no valor "/{id}"
	public ResponseEntity<User> findById(@PathVariable Long id){
		
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = userService.insert(obj);
		//Objeto URI serve para retornar para a tela o código 201, que é um cabeçalho http aceito na inserção.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id); 
		return ResponseEntity.noContent().build(); //Retorna o código 204, de deleção
	}
	
	@PutMapping(value = "/{id}") 
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = userService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
