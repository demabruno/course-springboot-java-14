package br.com.brunodemartini.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.course.entities.User;
import br.com.brunodemartini.course.repositories.UserRepository;
import br.com.brunodemartini.course.resources.UserResource;
/*
 * Para que uma classe possa ser usada como injeção de dependência em outras classes é necessário
 *      REGISTRÁ-LA para que o Springbot a reconheça com essa função. Existem várias annotations do java
 *      que a habilitam para tal serviço.
 * É necessário que a classe seja registrada para que a annootation @Autowired funcione nos componentes que
 *      forem implementá-la.
 *      
 *      Exemplo:
 *        @Component  - 
 *        @Service    - 
 *        @Repository -
 *        
 * A diferença entre as @'s acima é a semântica. Para cada tipo de componente há uma @ que é indicada. 
 *      Como essa é uma classe de serviço, a melhor @ para usar é a @Service
 */

/*
 * Logo, essa é uma classe do tipo Service Layer (camada S), do MVC. Ela fará interface entre a classe Controller,
 *       que é a UserResource e a classe Data Access Layer (camada Q), que nesse caso é a UserRepository.
 */

@Service 
public class UserService {
	
	//Para reconhecer a injeção de dependência. É como se estivesse instanciando a classe Q.
	@Autowired 
	private UserRepository userRepository;
	
	public List<User>  findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		
		//Optional é um campo Opcional? Existe desde o Java 8.
		Optional<User> optionalUser =  userRepository.findById(id);
		return optionalUser.get();
	}
}
