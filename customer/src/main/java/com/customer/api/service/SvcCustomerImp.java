package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.CustomerImage;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoCustomer;
import com.customer.api.repository.RepoCustomerList;
import com.customer.exception.ApiException;

@Service
public class SvcCustomerImp implements SvcCustomer {

	@Autowired
	RepoCustomer repo;

	@Autowired
	RepoCustomerList repoCustomerList;

	/**
     * Obtiene una lista de clientes activos.
     *
     * @return Una lista de objetos DtoCustomerList que representan los clientes activos.
     */
	@Override
	public List<DtoCustomerList> getCustomers() {
		return repoCustomerList.findByStatus(1);
	}

	/**
     * Obtiene un cliente por su RFC.
     *
     * @param rfc El RFC del cliente a obtener.
     * @return Un objeto Customer que representa el cliente.
     * @throws ApiException si el cliente no existe.
     */
	@Override
	public Customer getCustomer(String rfc) {
		Customer customer = repo.findByRfcAndStatus(rfc, 1);
		if (customer == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "customer does not exist");
		return customer;
	}

	/**
     * Crea un nuevo cliente.
     *
     * @param in El objeto Customer que contiene los datos del cliente a crear.
     * @return Un objeto ApiResponse que indica el resultado de la operación de creación de cliente.
     * @throws ApiException si el RFC o el correo electrónico del cliente ya existen.
     */
	@Override
	public ApiResponse createCustomer(Customer in) {
		in.setStatus(1);
		in.setCustomerImage(new CustomerImage());
		try {
			repo.save(in);
		} catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer rfc already exists");
			if (e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer mail already exists");
		}
		return new ApiResponse("customer created");
	}

	/**
    * Actualiza los datos de un cliente existente.
    *
    * @param in El objeto Customer que contiene los nuevos datos del cliente.
    * @param id El ID del cliente a actualizar.
    * @return Un objeto ApiResponse que indica el resultado de la operación de actualización de cliente.
    * @throws ApiException si el cliente no existe o si el RFC o el correo electrónico ya existen.
    */
	@Override
	public ApiResponse updateCustomer(Customer in, Integer id) {
		getCustomer(in.getRfc()); // Si el customer no existe mandará API Exception
		try {
			repo.updateCustomer(id, 
								in.getName(), 
								in.getSurname(), 
								in.getDate_birth(), 
								in.getRfc(), 
								in.getMail(),
								in.getAddress());
		} catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer rfc already exists");
			if (e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer mail already exists");
		}
		return new ApiResponse("customer updated");
	}

	/**
     * Elimina un cliente.
     *
     * @param id El ID del cliente a eliminar.
     * @return Un objeto ApiResponse que indica el resultado de la operación de eliminación de cliente.
     * @throws ApiException si el cliente no se puede eliminar.
     */
	@Override
	public ApiResponse deleteCustomer(Integer id) {
		if (repo.deleteCustomer(id) == 0)
			throw new ApiException(HttpStatus.BAD_REQUEST, "customer cannot be deleted");
		return new ApiResponse("customer removed");
	}

	/**
     * Actualiza la región de un cliente.
     *
     * @param region El objeto Region que representa la nueva región del cliente.
     * @param id El ID del cliente al que se actualizará la región.
     * @return Un objeto ApiResponse que indica el resultado de la operación de actualización de región de cliente.
     * @throws ApiException si la región no existe.
     */
	@Override
	public ApiResponse updateCustomerRegion(Region region, Integer id) {
		try {
			if (repo.updateCustomerRegion(region.getRegion_id(), id) == 0)
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer region cannot be updated");
			return new ApiResponse("customer region updated");
		} catch (DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "region not found");
		}
	}

}
