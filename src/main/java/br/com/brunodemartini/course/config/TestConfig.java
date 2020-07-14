package br.com.brunodemartini.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brunodemartini.course.entities.Order;
import br.com.brunodemartini.course.entities.User;
import br.com.brunodemartini.course.repositories.OrderRepository;
import br.com.brunodemartini.course.repositories.UserRepository;

//CommandLineRunner -> Interface que possui o método run(). Isso garante que o que estiver dentro dele será
//executado na inicialização.
@Configuration      //Marca a classe como sendo uma classe de configuração
@Profile("test")    //Marca a classe com perfil de configuração. O "test" deve ser o conteúdo que está no atributo spring.profiles.active do arquivo application.properties
public class TestConfig implements CommandLineRunner{

	@Autowired //Marca com send um atributo para injeção de dependência.
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		//A data 2019-06-20T19:53:07Z está no padrão ISO 8601
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

}
