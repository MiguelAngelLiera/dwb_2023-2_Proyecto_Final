package com.customer.api.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Customer;

@Repository
public interface RepoCustomer  extends JpaRepository<Customer, Integer>{
	
	/**
     * Busca un cliente por RFC y estado.
     *
     * @param rfc    El RFC del cliente a buscar.
     * @param status El estado del cliente.
     * @return El cliente encontrado.
     */
	Customer findByRfcAndStatus(String rfc, Integer status);
	
	/**
     * Actualiza los datos de un cliente.
     *
     * @param customer_id  El ID del cliente a actualizar.
     * @param name         El nuevo nombre del cliente.
     * @param surname      El nuevo apellido del cliente.
     * @param date_birth   La nueva fecha de nacimiento del cliente.
     * @param rfc          El nuevo RFC del cliente.
     * @param mail         El nuevo correo electrónico del cliente.
     * @param address      La nueva dirección del cliente.
     * @return El número de filas afectadas.
     */
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer "
					+ " SET name = :name, "
						+  "surname = :surname, "
						+  "date_birth = :date_birth, "
						+  "rfc = :rfc, "
						+  "mail = :mail, "
						+  "address = :address "
					+ "WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer updateCustomer(
			@Param("customer_id") Integer customer_id,
			@Param("name") String name,
			@Param("surname") String surname,
			@Param("date_birth") LocalDate date_birth,
			@Param("rfc") String rfc,
			@Param("mail") String mail,
			@Param("address") String address);
	
	/**
     * Elimina un cliente.
     *
     * @param customer_id El ID del cliente a eliminar.
     * @return El número de filas afectadas.
     */
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer SET status = 0 WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer deleteCustomer(@Param("customer_id") Integer customer_id);
	
	 /**
     * Actualiza la región de un cliente.
     *
     * @param region_id    El ID de la región a asignar al cliente.
     * @param customer_id  El ID del cliente a actualizar.
     * @return El número de filas afectadas.
     */
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer SET region_id = :region_id WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer updateCustomerRegion(@Param("region_id") Integer region_id, @Param("customer_id") Integer customer_id);
}
