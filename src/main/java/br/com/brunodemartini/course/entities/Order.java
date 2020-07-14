package br.com.brunodemartini.course.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity                   // Marca classe como sendo uma entidade. Dessa forma, o JPA cria a tabela n BD.
@Table(name = "tb_order") // Define o nome da classe que será criada no BD.
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id  //PK da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Marca como sendo uma PK auto incrementável
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant date;
	
	/*
	 * @ManyToOne --> Marca client como atributo que é usado no relacionamento entre a tabela Oder e User.
	 *                Nesse caso, Cliente pode ter varios pedidos.
	 */
	@ManyToOne 
	@JoinColumn(name = "client_id") //Dá o nome ao atributo que será a FK da tabela.
	private User client;
	
	@OneToOne
	@JoinColumn(name  = "payment_id") //???
	private Payment paymment;
	
	public Order() {}

	public Order(Long id, Instant date, User client) {
		this.id = id;
		this.date = date;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		Order other = (Order) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
