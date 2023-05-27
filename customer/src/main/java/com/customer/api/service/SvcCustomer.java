package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.Region;

public interface SvcCustomer {

	/**
     * Obtiene una lista de clientes.
     *
     * @return Una lista de objetos DtoCustomerList que representan a los clientes.
     */
	List<DtoCustomerList> getCustomers();

	/**
     * Obtiene un cliente por su RFC.
     *
     * @param rfc El RFC del cliente a buscar.
     * @return El objeto Customer que corresponde al RFC especificado.
     */
	Customer getCustomer(String rfc);

	/**
     * Crea un nuevo cliente.
     *
     * @param in El objeto Customer que representa al cliente a crear.
     * @return Un objeto ApiResponse que indica el resultado de la operación de creación.
     */
	ApiResponse createCustomer(Customer in);

	/**
     * Actualiza un cliente existente.
     *
     * @param in El objeto Customer que contiene los datos actualizados del cliente.
     * @param id El ID del cliente a actualizar.
     * @return Un objeto ApiResponse que indica el resultado de la operación de actualización.
     */
	ApiResponse updateCustomer(Customer in, Integer id);

	/**
     * Elimina un cliente.
     *
     * @param id El ID del cliente a eliminar.
     * @return Un objeto ApiResponse que indica el resultado de la operación de eliminación.
     */
	ApiResponse deleteCustomer(Integer id);

	/**
     * Actualiza la región de un cliente.
     *
     * @param region El objeto Region que representa la nueva región del cliente.
     * @param id El ID del cliente al que se le actualizará la región.
     * @return Un objeto ApiResponse que indica el resultado de la operación de actualización de región.
     */
	ApiResponse updateCustomerRegion(Region region, Integer id);
}
