package com.invoice.api.entity;

import java.time.LocalDateTime;

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
 * Representa una factura.
 */
@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("invoice_id")
	@Column(name = "invoice_id")
	private Integer invoice_id;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc required")
	private String rfc;
	
	@JsonProperty("subtotal")
	@Column(name = "subtotal")
	@NotNull(message="subtotal required")
	private Double subtotal;
	
	@JsonProperty("taxes")
	@Column(name = "taxes")
	@NotNull(message="taxes required")
	private Double taxes;
	
	@JsonProperty("total")
	@Column(name = "total")
	@NotNull(message="total required")
	private Double total;
	
	@Column(name = "created_at")
	@JsonProperty("created_at")
	@NotNull(message="created_at required")
	private LocalDateTime created_at;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	/**
	 * Crea una nueva instancia de Invoice.
	 */
	public Invoice() {
		
	}

	/**
	 * Obtiene el ID de la factura.
	 *
	 * @return El ID de la factura
	 */
	public Integer getInvoice_id() {
		return invoice_id;
	}

	/**
	 * Establece el ID de la factura.
	 *
	 * @param invoice_id El ID de la factura a establecer
	 */
	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
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
	 * Obtiene el subtotal.
	 *
	 * @return El subtotal
	 */
	public Double getSubtotal() {
		return subtotal;
	}

	/**
	 * Establece el subtotal.
	 *
	 * @param subtotal El subtotal a establecer
	 */
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * Obtiene los impuestos.
	 *
	 * @return Los impuestos
	 */
	public Double getTaxes() {
		return taxes;
	}

	/**
	 * Establece los impuestos.
	 *
	 * @param taxes Los impuestos a establecer
	 */
	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	/**
	 * Obtiene el total.
	 *
	 * @return El total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * Establece el total.
	 *
	 * @param total El total a establecer
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * Obtiene la fecha de creación.
	 *
	 * @return La fecha de creación
	 */
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	/**
	 * Establece la fecha de creación.
	 *
	 * @param created_at La fecha de creación a establecer
	 */
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
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
