package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;

public interface SvcRegion {

	/**
     * Obtiene una lista de regiones.
     *
     * @return Una lista de objetos Region que representan las regiones.
     */
	List<Region> getRegions();

	/**
     * Obtiene una región por su ID.
     *
     * @param region_id El ID de la región a buscar.
     * @return El objeto Region que representa a la región.
     */
	Region getRegion(Integer region_id);

	/**
     * Crea una nueva región.
     *
     * @param region El objeto Region que contiene los datos de la región a crear.
     * @return Un objeto ApiResponse que indica el resultado de la operación de creación de región.
     */
	ApiResponse createRegion(Region region);

	/**
     * Actualiza una región existente.
     *
     * @param region_id El ID de la región a actualizar.
     * @param region El objeto Region que contiene los nuevos datos de la región.
     * @return Un objeto ApiResponse que indica el resultado de la operación de actualización de región.
     */
	ApiResponse updateRegion(Integer region_id, Region region);

	/**
     * Elimina una región.
     *
     * @param region_id El ID de la región a eliminar.
     * @return Un objeto ApiResponse que indica el resultado de la operación de eliminación de región.
     */
	ApiResponse deleteRegion(Integer region_id);
}
