package br.com.brunodemartini.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.course.entities.User;

//Indica que a classe é um recurso web que implementada por um controlador Rest
@RestController
//Indica o caminho para acessar o recurso
@RequestMapping(value="/users")
public class UserResource {
	
	@GetMapping //Indica que é um  método que responde a uma chammada get do http
	//ResponseEntity é um tipo padrão de retorno de recursos web para um recurso
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "bruno", "bruno@gmail.com", "999999", "123456");
		//.OK() indica uma resposta com sucesso da web.
		//.body indica o corpo da resposta.
		return ResponseEntity.ok().body(u);
	}

}
