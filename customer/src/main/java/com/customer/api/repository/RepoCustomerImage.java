package com.customer.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.CustomerImage;

@Repository
public interface RepoCustomerImage extends JpaRepository<CustomerImage, Integer>{

	/**
     * Establece la imagen de un cliente.
     *
     * @param customer_image_id El ID de la imagen del cliente.
     * @param customer_image    La imagen del cliente en formato de cadena.
     * @return El número de filas afectadas.
     */
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_image SET customer_image = :customer_image WHERE customer_image_id = :customer_image_id ", nativeQuery = true)
	Integer setCustomerImage(@Param("customer_image_id") Integer customer_image_id, @Param("customer_image") String customer_image);
}