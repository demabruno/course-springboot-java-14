package br.com.brunodemartini.course.enums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum EnumOrderStatus {
	WAITING_PAYMENT(1), 
	PAID(2), 
	SHIPPED(3), 
	DELIVERED(4), 
	CANCELED(5);
	
	/*
	 * Necessário enumerar os itens do Enum porque, quando insere o atributo na base de dados
	 *            ele insere um número que representa o enum.
	 */
	
	private int code;
	
	private EnumOrderStatus (int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static EnumOrderStatus valueOf(int code) {
		for(EnumOrderStatus value : EnumOrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Order Status Code.");
	}
}
