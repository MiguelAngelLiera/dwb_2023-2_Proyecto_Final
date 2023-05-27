package com.customer.api.entity;

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
/**
 * Esta clase representa la entidad de una región.
 */
@Entity
@Table(name ="region")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Integer region_id;
	
	@NotNull
	@Column(name = "region")
	private String region;
	
	@Column(name = "status")
	@Min(value = 0, message="status must be 0 or 1")
	@Max(value = 1, message="status must be 0 or 1")
	@JsonIgnore
	private Integer status;
	
	 /**
     * Crea una instancia de la clase Region.
     */ 
	public Region() {
		
	}

	/**
     * Obtiene el ID de la región.
     *
     * @return El ID de la región.
     */
	public Integer getRegion_id() {
		return region_id;
	}

	/**
     * Establece el ID de la región.
     *
     * @param region_id El ID de la región a establecer.
     */
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	/**
     * Obtiene el nombre de la región.
     *
     * @return El nombre de la región.
     */
	public String getRegion() {
		return region;
	}

	/**
     * Establece el nombre de la región.
     *
     * @param region El nombre de la región a establecer.
     */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
     * Obtiene el estado de la región.
     *
     * @return El estado de la región.
     */
	public Integer getStatus() {
		return status;
	}

	/**
     * Establece el estado de la región.
     *
     * @param status El estado de la región a establecer.
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
     * Devuelve una representación en cadena de la región.
     *
     * @return Una representación en cadena de la región.
     */
	@Override
	public String toString() {
		return "Region [region_id=" + region_id + ", region=" + region + ", status=" + status + "]";
	}
}
