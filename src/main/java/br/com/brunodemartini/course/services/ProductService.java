package br.com.brunodemartini.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.course.entities.Product;
import br.com.brunodemartini.course.repositories.ProductRepository;

/*
 * Logo, essa é uma classe do tipo Service Layer (camada S), do MVC. Ela fará interface entre a classe Controller,
 *       que é a ProductResource e a classe Data Access Layer (camada Q), que nesse caso é a ProductRepository.
 */

@Service 
public class ProductService {
	
	//Para reconhecer a injeção de dependência. É como se estivesse instanciando a classe Q.
	@Autowired 
	private ProductRepository productRepository;
	
	public List<Product>  findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		
		//Optional é um campo Opcional? Existe desde o Java 8.
		Optional<Product> optionalProduct =  productRepository.findById(id);
		return optionalProduct.get();
	}
}
