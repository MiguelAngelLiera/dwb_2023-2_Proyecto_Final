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
 * Representa un item en una factura.
 */
@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("item_id")
	@Column(name = "item_id")
	private Integer item_id;
	
	@JsonProperty("invoice_id")
	@Column(name = "invoice_id")
	@NotNull(message="invoice id required")
	private Integer id_invoice;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin code required")
	private String gtin;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity required")
	@Min(value=1, message="quantity must be greater than 0")
	private Integer quantity;
	
	@JsonProperty("unit_price")
	@Column(name = "unit_price")
	@NotNull(message="unit price required")
	private Double unit_price;
	
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
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;

	/**
	 * Obtiene el ID del ítem.
	 *
	 * @return El ID del ítem
	 */
	public Integer getItem_id() {
		return item_id;
	}

	/**
	 * Establece el ID del ítem.
	 *
	 * @param item_id El ID del ítem a establecer
	 */
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	/**
	 * Obtiene el ID de la factura.
	 *
	 * @return El ID de la factura
	 */
	public Integer getId_invoice() {
		return id_invoice;
	}

	/**
	 * Establece el ID de la factura.
	 *
	 * @param id_invoice El ID de la factura a establecer
	 */
	public void setId_invoice(Integer id_invoice) {
		this.id_invoice = id_invoice;
	}

	/**
	 * Obtiene el código GTIN.
	 *
	 * @return El código GTIN
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * Establece el código GTIN.
	 *
	 * @param gtin El código GTIN a establecer
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
	 * Obtiene el precio unitario.
	 *
	 * @return El precio unitario
	 */
	public Double getUnit_price() {
		return unit_price;
	}

	/**
	 * Establece el precio unitario.
	 *
	 * @param unit_price El precio unitario a establecer
	 */
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
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
