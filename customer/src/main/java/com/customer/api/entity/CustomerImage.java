package com.customer.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Esta clase representa la entidad de una imagen de cliente.
 */
@Entity
@Table(name = "customer_image")
public class CustomerImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_image_id")
	private Integer customer_image_id;
	
	@NotNull
	@Column(name = "customer_image")
	private String customer_image;
	
	/**
     * Crea una instancia de CustomerImage con valores predeterminados.
     * El valor de customer_image se establece como una cadena vacía.
     */
	public CustomerImage() {
		this.customer_image = "";
	}

	 /**
     * Obtiene el ID de la imagen de cliente.
     *
     * @return El ID de la imagen de cliente.
     */
	public Integer getCustomer_image_id() {
		return customer_image_id;
	}
	
	/**
     * Establece el ID de la imagen de cliente.
     *
     * @param customer_image_id El ID de la imagen de cliente a establecer.
     */
	public void setCustomer_image_id(Integer customer_image_id) {
		this.customer_image_id = customer_image_id;
	}

	/**
     * Obtiene la ruta o nombre de la imagen de cliente.
     *
     * @return La ruta o nombre de la imagen de cliente.
     */
	public String getCustomer_image() {
		return customer_image;
	}

	 /**
     * Establece la ruta o nombre de la imagen de cliente.
     *
     * @param customer_image La ruta o nombre de la imagen de cliente a establecer.
     */
	public void setCustomer_image(String customer_image) {
		this.customer_image = customer_image;
	}

}
