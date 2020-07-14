package br.com.brunodemartini.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.course.entities.Order;
import br.com.brunodemartini.course.repositories.OrderRepository;
import br.com.brunodemartini.course.services.OrderService;

//@RestController                 --> Indica que a classe é um recurso web que implementada por um controlador Rest
//@RequestMapping(value="/users") --> Indica o caminho para acessar o recurso

/* Logo, a Classe Order Resource é a classe Controller, que fará a interface entre a aplicação 
 *       e a camada Service Layer, ou seja, de regra de negócio, que nesse caso é a OrderService.
 */

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired //Diz que a classe OrderRespository é uma classe de injeção de dependência
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	//@GetMapping    --> Indica que é um  método que responde a uma chammada get() do http.
	//ResponseEntity --> É um tipo padrão de retorno de recursos web para um recurso.
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> listOrders  = orderRepository.findAll();
		
		//.OK() --> indica uma resposta com sucesso da web.
		//.body --> indica o corpo da resposta.
		return ResponseEntity.ok().body(listOrders);
	}
	
	// Marcação do GetMapping que define que o atributo id será passado por parâmetro na URL pelo GET()
	@GetMapping(value = "/{id}") 
	//@PathVariable --> Indica que o parâmetro recebido (id) é o valor que virá pelo get() no valor "/{id}"
	public ResponseEntity<Order> findById(@PathVariable Long id){
		
		Order order = orderService.findById(id);
		
		return ResponseEntity.ok().body(order);
	}

}
