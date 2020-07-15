package br.com.brunodemartini.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.course.entities.Product;
import br.com.brunodemartini.course.repositories.ProductRepository;
import br.com.brunodemartini.course.services.ProductService;

//@RestController                 --> Indica que a classe é um recurso web que implementada por um controlador Rest
//@RequestMapping(value="/users") --> Indica o caminho para acessar o recurso

/* Logo, a Classe Product Resource é a classe Controller, que fará a interface entre a aplicação 
 *       e a camada Service Layer, ou seja, de regra de negócio, que nesse caso é a ProductService.
 */

@RestController
@RequestMapping(value="/products")
public class ProductResource {
	
	@Autowired //Diz que a classe ProductRespository é uma classe de injeção de dependência
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	//@GetMapping    --> Indica que é um  método que responde a uma chammada get() do http.
	//ResponseEntity --> É um tipo padrão de retorno de recursos web para um recurso.
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> listProducts  = productRepository.findAll();
		
		//.OK() --> indica uma resposta com sucesso da web.
		//.body --> indica o corpo da resposta.
		return ResponseEntity.ok().body(listProducts);
	}
	
	// Marcação do GetMapping que define que o atributo id será passado por parâmetro na URL pelo GET()
	@GetMapping(value = "/{id}") 
	//@PathVariable --> Indica que o parâmetro recebido (id) é o valor que virá pelo get() no valor "/{id}"
	public ResponseEntity<Product> findById(@PathVariable Long id){
		
		Product product = productService.findById(id);
		
		return ResponseEntity.ok().body(product);
	}

}
