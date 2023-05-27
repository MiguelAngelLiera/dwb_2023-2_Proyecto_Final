package com.customer.api.dto;

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
 * Esta clase representa un objeto DTO (Data Transfer Object) utilizado para listar clientes.
 */
@Entity
@Table(name = "customer")
public class DtoCustomerList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("customer_id")
	@Column(name = "customer_id")
	private Integer customer_id;
	
	@JsonProperty("name")
	@Column(name = "name")
	@NotNull(message="name is required")
	private String name;
	
	@JsonProperty("surname")
	@Column(name = "surname")
	@NotNull(message="surname is required")
	private String surname;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	private String rfc;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value = 0, message="status must be 0 or 1")
	@Max(value = 1, message="status must be 0 or 1")
	private Integer status;

	/**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
	public Integer getCustomer_id() {
		return customer_id;
	}

	/**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	/**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
	public String getName() {
		return name;
	}

	/**
     * Establece el nombre del cliente.
     *
     * @param name El nombre del cliente.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Obtiene el apellido del cliente.
     *
     * @return El apellido del cliente.
     */
	public String getSurname() {
		return surname;
	}

	/**
     * Establece el apellido del cliente.
     *
     * @param surname El apellido del cliente.
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
     * Obtiene el RFC del cliente.
     *
     * @return El RFC del cliente.
     */
	public String getRfc() {
		return rfc;
	}

	/**
     * Establece el RFC del cliente.
     *
     * @param rfc El RFC del cliente.
     */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
     * Obtiene el estado del cliente.
     *
     * @return El estado del cliente.
     */
	public Integer getStatus() {
		return status;
	}

	/**
     * Establece el estado del cliente.
     *
     * @param status El estado del cliente.
     */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
