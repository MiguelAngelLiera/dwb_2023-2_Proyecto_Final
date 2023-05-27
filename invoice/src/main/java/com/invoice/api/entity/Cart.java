package com.invoice.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa un carrito de compras.
 */
@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cart_id")
	@Column(name = "cart_id")
	private Integer cart_id;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	private String rfc;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin is required")
	private String gtin;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity is required")
	private Integer quantity;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;

	/**
	 * Obtiene el ID del carrito.
	 *
	 * @return El ID del carrito
	 */
	public Integer getCart_id() {
		return cart_id;
	}

	/**
	 * Establece el ID del carrito.
	 *
	 * @param cart_id El ID del carrito a establecer
	 */
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	/**
	 * Obtiene el RFC.
	 *
	 * @return El RFC
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Establece el RFC.
	 *
	 * @param rfc El RFC a establecer
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * Obtiene el GTIN.
	 *
	 * @return El GTIN
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * Establece el GTIN.
	 *
	 * @param gtin El GTIN a establecer
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	/**
	 * Obtiene la cantidad.
	 *
	 * @return La cantidad
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Establece la cantidad.
	 *
	 * @param quantity La cantidad a establecer
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Obtiene el estado.
	 *
	 * @return El estado
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Establece el estado.
	 *
	 * @param status El estado a establecer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}