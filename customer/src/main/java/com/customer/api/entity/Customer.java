package com.customer.api.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Clase para representar a un cliente.
 * Será una tabla en una vase de datos.
 */
@Entity
@Table(name = "customer")
public class Customer {

	/* El id del cliente, éste es único. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("customer_id")
	@Column(name = "customer_id")
	private Integer customer_id;
	
	/* El nombre del cliente. */
	@JsonProperty("name")
	@Column(name = "name")
	@NotNull(message="name is required")
	private String name;
	
	/* El apellido del cliente. */
	@JsonProperty("surname")
	@Column(name = "surname")
	@NotNull(message="surname is required")
	private String surname;
	
	/* La fecha de nacimiento del cliente. */
	@JsonProperty("date_birth")
	@Column(name = "date_birth")
	@NotNull(message="date_birth is required")
	private LocalDate date_birth;
	
	/* El RFC del cliente. */
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	private String rfc;
	
	/* La dirección electrónica del correo. */
	@JsonProperty("mail")
	@Column(name = "mail")
	@NotNull(message="mail is required")
	private String mail;
	
	/* La dirección física del cliente. */
	@JsonProperty("address")
	@Column(name = "address")
	private String address;
	
	/* Status. */
	@JsonIgnore
	@Column(name = "status")
	@Min(value = 0, message="status must be 0 or 1")
	@Max(value = 1, message="status must be 0 or 1")
	private Integer status;
	
	/* La región del cliente. */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;
	
	/* La imagen del cliente. */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_image_id", referencedColumnName = "customer_image_id")
	private CustomerImage customerImage;

	/*
	 * Devolvemos el ID del cliente.
	 */
	public Integer getCustomer_id() {
		return customer_id;
	}

	/*
	 * Establecemos el ID del cliente.
	 */
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	/*
	 * Regresamos el nombre de pila del cliente.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Establecemos el nombre de pila del cliente.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Regresamos el apellido del cliente.
	 */
	public String getSurname() {
		return surname;
	}

	/*
	 * Regresamos el apellido del cliente.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/*
	 * Regresamos la fecha de nacimiento del cliente.
	 */
	public LocalDate getDate_birth() {
		return date_birth;
	}

	/*
	 * Establecemos la fecha de nacimiento del cliente.
	 */
	public void setDate_birth(LocalDate date_birth) {
		this.date_birth = date_birth;
	}

	/*
	 * Regresamos el RFC.
	 */
	public String getRfc() {
		return rfc;
	}

	/*
	 * Establecemos el RFC.
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*
	 * Regresamos la dirección de correo electrónica del cliente.
	 */
	public String getMail() {
		return mail;
	}

	/*
	 * Establecemos la dirección de correo electrónica del cliente.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/*
	 * Regresamos la dirección física del cliente.
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * Establecemos la dirección física del cliente.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * Regresamos el status del cliente.
	 */
	public Integer getStatus() {
		return status;
	}

	/*
	 * Establecemos el status del cliente.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*
	 * Regresamos la región del cliente.
	 */
	public Region getRegion() {
		return region;
	}

	/*
	 * Establecemos la región del cliente.
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/*
	 * Regresamos la imagen del cliente.
	 */
	public CustomerImage getCustomerImage() {
		return customerImage;
	}

	/*
	 * Establecemos la imagen del cliente.
	 */
	public void setCustomerImage(CustomerImage customerImage) {
		this.customerImage = customerImage;
	}

	/*
	 * Implementación del toString.
	 */
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", surname=" + surname + ", date_birth="
				+ date_birth + ", rfc=" + rfc + ", mail=" + mail + ", address=" + address + ", status=" + status
				+ ", region=" + region + ", customerImage=" + customerImage + "]";
	}	
	
}