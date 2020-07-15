package br.com.brunodemartini.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.course.entities.Category;
import br.com.brunodemartini.course.repositories.CategoryRepository;

/*
 * Logo, essa é uma classe do tipo Service Layer (camada S), do MVC. Ela fará interface entre a classe Controller,
 *       que é a CategoryResource e a classe Data Access Layer (camada Q), que nesse caso é a CategoryRepository.
 */

@Service 
public class CategoryService {
	
	//Para reconhecer a injeção de dependência. É como se estivesse instanciando a classe Q.
	@Autowired 
	private CategoryRepository categoryRepository;
	
	public List<Category>  findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		
		//Optional é um campo Opcional? Existe desde o Java 8.
		Optional<Category> optionalCategory =  categoryRepository.findById(id);
		return optionalCategory.get();
	}
}
