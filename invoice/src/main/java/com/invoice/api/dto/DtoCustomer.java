package com.invoice.api.dto;
/**
 * Esta clase representa un objeto DTO (Data Transfer Object) para los datos de un cliente.
 */
public class DtoCustomer {

	/* El RFC */
	private String rfc;

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
     * @param rfc El RFC del cliente a establecer.
     */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

}
