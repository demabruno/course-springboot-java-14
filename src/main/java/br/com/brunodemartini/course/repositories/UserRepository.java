package br.com.brunodemartini.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunodemartini.course.entities.User;

//User repository é a camada de baixo que faz a interface com os dados (do banco?)
public interface UserRepository extends JpaRepository<User, Long>{

}
