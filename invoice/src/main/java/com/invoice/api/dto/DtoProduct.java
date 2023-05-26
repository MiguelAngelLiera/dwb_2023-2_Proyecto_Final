package com.invoice.api.dto;

/*
 * Requerimiento 3
 * Agregar atributos de clase para la validación del producto
 */
public class DtoProduct {

	/* gtin */
    private String gtin;
	/* El stock del producto */
	private Integer stock;
	/* El precio del producto. */
	private Double price;

	/*
	 * Devolvemos el GTIN del producto.
	 */
	public String getGtin() {
		return gtin;
	}

	/*
	 * Establecemos el GTIN del producto.
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	/*
	 * Devolvemos el stock del producto.
	 */
	public Integer getStock() {
		return stock;
	}

	/*
	 * Establecemos el stock del producto.
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/*
	 * Devolvemos el precio del producto.
	 */
	public Double getPrice() {
		return price;
	}

	/*
	 * Establecemos el precio del producto.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/*
	 * Implementación de toString.
	 */
	@Override
	public String toString() {
		return "DtoProduct [gtin=" + gtin + ", stock=" + stock + ", price=" + price + "]";
	}

	/**
	 * Implementación del método equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtoProduct other = (DtoProduct) obj;
		if (gtin == null) {
			if (other.gtin != null)
				return false;
		} else if (!gtin.equals(other.gtin))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	

}
