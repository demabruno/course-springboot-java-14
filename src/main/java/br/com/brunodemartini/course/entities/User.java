package br.com.brunodemartini.course.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //Declara a classe como entiade.
@Table(name = "tb_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //Declara o atributo como sendo a PK.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Define atributo como auto incremento
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	//*@OneToMany(mappedBy = "client") --> Mapeia o nome do atributo ao qual referecia na tabela Order
	/*
	 * @JsonIgnore --> Evita que, quando serializado pelo Jackson, do Postmann, o Springboot fique em 
	 *                 loop infinito enviando dados a serem serializados para mostrar no browser. 
	 *                 Dessa forma, usando a tag @JsonIgnore o java entende que quando carregado os dados
	 *                 de um pedido (Order) serão exibidos os dados do cliente que o solicitou. No entanto,
	 *                 se solicitar os dados do usuário não mostrará os pedidos associados a esse cliente.
	 *                 Se não usar essa tag fica um loop infinito de exibição de dadosp por causa do relacionamento
	 *                 entre as tabelas Order e User.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders;
	
	public User() {}

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ "]";
	}
}
