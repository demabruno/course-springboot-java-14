package br.com.brunodemartini.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunodemartini.course.entities.Category;
import br.com.brunodemartini.course.entities.Product;

//User repository é a camada de baixo que faz a interface com os dados (do banco). 
//É como se fosse uma camada Q. A inteface JpaRepository já implementa vários métodos de forma padrão
//  para serem usados.
public interface ProductRepository extends JpaRepository<Product, Long>{

}
