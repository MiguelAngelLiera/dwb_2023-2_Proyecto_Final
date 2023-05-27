package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;
import com.customer.exception.ApiException;

/**
 * Implementación de la interfaz SvcRegion que proporciona operaciones relacionadas con regiones.
 */
@Service
public class SvcRegionImp implements SvcRegion {

	@Autowired
	RepoRegion repo;
	
	/**
	 * Recupera una lista de regiones activas.
	 *
	 * @return Lista de regiones activas
	 */
	@Override
	public List<Region> getRegions() {
		return repo.findByStatus(1);
	}

	/**
	 * Recupera una región específica por su ID.
	 *
	 * @param region_id El ID de la región a recuperar
	 * @return La región con el ID especificado
	 * @throws ApiException si la región no existe
	 */
	@Override
	public Region getRegion(Integer region_id) {
		Region region = repo.findByRegionId(region_id);
		if(region == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		return region;
	}

	/**
	 * Crea una nueva región.
	 *
	 * @param region La región a crear
	 * @return ApiResponse que indica el resultado de la operación
	 * @throws ApiException si la región ya existe o no está activa
	 */
	@Override
	public ApiResponse createRegion(Region region) {
		Region regionSaved = (Region) repo.findByRegion(region.getRegion());
		if(regionSaved != null) {
			if(regionSaved.getStatus() == 0) {
				repo.activateRegion(regionSaved.getRegion_id());
				return new ApiResponse("region has been activated");
			}
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "region already exists");
		}
		repo.createRegion(region.getRegion());
		return new ApiResponse("region created");
	}

	/**
	 * Actualiza una región existente.
	 *
	 * @param region_id El ID de la región a actualizar
	 * @param region Los datos de la región actualizada
	 * @return ApiResponse que indica el resultado de la operación
	 * @throws ApiException si la región no existe, no está activa o la región actualizada ya existe
	 */
	@Override
	public ApiResponse updateRegion(Integer region_id, Region region) {
		Region regionSaved = (Region) repo.findByRegionId(region_id);
		
		if(regionSaved == null) 
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		
		if(regionSaved.getStatus() == 0)
			throw new ApiException(HttpStatus.BAD_REQUEST, "region is not active");
		
		regionSaved = (Region) repo.findByRegion(region.getRegion());
		if(regionSaved != null)
			throw new ApiException(HttpStatus.BAD_REQUEST, "region already exists");
		
		repo.updateRegion(region_id, region.getRegion());
		return new ApiResponse("region updated");

	}

	/**
	 * Elimina una región.
	 *
	 * @param region_id El ID de la región a eliminar
	 * @return ApiResponse que indica el resultado de la operación
	 * @throws ApiException si la región no existe
	 */
	@Override
	public ApiResponse deleteRegion(Integer region_id) {
		Region regionSaved = (Region) repo.findByRegionId(region_id);
		if(regionSaved == null) 
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		repo.deleteById(region_id);
		return new ApiResponse("region removed");
	}

}
