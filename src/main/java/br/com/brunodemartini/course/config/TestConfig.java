package br.com.brunodemartini.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brunodemartini.course.entities.Category;
import br.com.brunodemartini.course.entities.Order;
import br.com.brunodemartini.course.entities.OrderItem;
import br.com.brunodemartini.course.entities.Payment;
import br.com.brunodemartini.course.entities.Product;
import br.com.brunodemartini.course.entities.User;
import br.com.brunodemartini.course.enums.EnumOrderStatus;
import br.com.brunodemartini.course.repositories.CategoryRepository;
import br.com.brunodemartini.course.repositories.OrderItemRepository;
import br.com.brunodemartini.course.repositories.OrderRepository;
import br.com.brunodemartini.course.repositories.ProductRepository;
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
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p3.getCategories().add(cat1);
		p4.getCategories().add(cat3);
		p4.getCategories().add(cat1);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		//A data 2019-06-20T19:53:07Z está no padrão ISO 8601
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), EnumOrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), EnumOrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), EnumOrderStatus.CANCELED, u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pp1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayent(pp1);
		orderRepository.save(o1);
		
		
	}

}
