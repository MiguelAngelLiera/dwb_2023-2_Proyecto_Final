package com.customer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.Region;
import com.customer.api.service.SvcCustomer;
import com.customer.exception.ApiException;

/**
 * Esta clase es el controlador para las operaciones relacionadas con los clientes.
 */
@RestController
@RequestMapping("/customer")
public class CtrlCustomer {

	@Autowired
	SvcCustomer svc;

	/**
     * Obtiene una lista de todos los clientes.
     *
     * @return Una respuesta con una lista de objetos DtoCustomerList y el estado HTTP OK.
     */
	@GetMapping
	public ResponseEntity<List<DtoCustomerList>> getCustomers() {
		return new ResponseEntity<List<DtoCustomerList>>(svc.getCustomers(), HttpStatus.OK);
	}

	/**
     * Obtiene un cliente por su RFC.
     *
     * @param rfc El RFC del cliente.
     * @return Una respuesta con el objeto Customer y el estado HTTP OK.
     */
	@GetMapping("/{rfc}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("rfc") String rfc) {
		return new ResponseEntity<Customer>(svc.getCustomer(rfc), HttpStatus.OK);
	}

	/**
     * Crea un nuevo cliente.
     *
     * @param in             El objeto Customer a crear.
     * @param bindingResult  El resultado de la validación del objeto Customer.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se crea correctamente.
     * @throws ApiException Si hay errores de validación, se lanza una ApiException con el mensaje de error.
     */
	@PostMapping
	public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody Customer in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<ApiResponse>(svc.createCustomer(in), HttpStatus.OK);
	}

	/**
     * Actualiza un cliente existente.
     *
     * @param id             El ID del cliente a actualizar.
     * @param in             El objeto Customer con los datos actualizados.
     * @param bindingResult  El resultado de la validación del objeto Customer.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se actualiza correctamente.
     * @throws ApiException Si hay errores de validación, se lanza una ApiException con el mensaje de error.
     */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("id") Integer id, @Valid @RequestBody Customer in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<ApiResponse>(svc.updateCustomer(in, id), HttpStatus.OK);
	}

	/**
     * Elimina un cliente existente.
     *
     * @param id El ID del cliente a eliminar.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se elimina correctamente.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id") Integer id) {
		return new ResponseEntity<ApiResponse>(svc.deleteCustomer(id), HttpStatus.OK);
	}

	/**
     * Actualiza la región de un cliente.
     *
     * @param id El ID del cliente a actualizar.
     * @param in El objeto Region con la región actualizada.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se actualiza correctamente.
     */
	@PutMapping("/{id}/region")
	public ResponseEntity<ApiResponse> updateCustomerRegion(@PathVariable("id") Integer id, @RequestBody Region in) {
		return new ResponseEntity<ApiResponse>(svc.updateCustomerRegion(in, id), HttpStatus.OK);
	}
}
