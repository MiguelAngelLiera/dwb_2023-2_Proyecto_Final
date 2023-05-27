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
import com.customer.api.entity.Region;
import com.customer.api.service.SvcRegion;
import com.customer.exception.ApiException;

/**
 * Esta clase es el controlador para las operaciones relacionadas con la región.
 */
@RestController
@RequestMapping("/region")
public class CtrlRegion {
	
	@Autowired
	SvcRegion svc;
	
	/**
     * Obtiene todas las regiones.
     *
     * @return Una respuesta con una lista de objetos Region y el estado HTTP OK si se obtienen las regiones correctamente.
     */
	@GetMapping
	public ResponseEntity<List<Region>> getRegions(){		
		return new ResponseEntity<List<Region>>(svc.getRegions(), HttpStatus.OK);
	}
	
	/**
     * Obtiene una región específica por su identificador de región.
     *
     * @param region_id El identificador de la región a obtener.
     * @return Una respuesta con el objeto Region correspondiente y el estado HTTP OK si se obtiene la región correctamente.
     */
	@GetMapping("/{region_id}")
	public ResponseEntity<Region> getRegion(@PathVariable int region_id){
		 return new ResponseEntity<Region>(svc.getRegion(region_id), HttpStatus.OK);
	}
	
	/**
     * Crea una nueva región.
     *
     * @param region         El objeto Region que contiene los datos de la región a crear.
     * @param bindingResult  El resultado de la validación del objeto Region.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se crea la región correctamente.
     * @throws ApiException Si hay errores de validación, se lanza una ApiException con el mensaje de error.
     */
	@PostMapping
	public ResponseEntity<ApiResponse> createRegion(@Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<ApiResponse>(svc.createRegion(region), HttpStatus.OK);
	}
	
	/**
     * Actualiza una región existente.
     *
     * @param region_id      El identificador de la región a actualizar.
     * @param region         El objeto Region que contiene los nuevos datos de la región.
     * @param bindingResult  El resultado de la validación del objeto Region.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se actualiza la región correctamente.
     * @throws ApiException Si hay errores de validación, se lanza una ApiException con el mensaje de error.
     */
	@PutMapping("/{region_id}")
	public ResponseEntity<ApiResponse> updateRegion(@PathVariable int region_id, @Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<ApiResponse>(svc.updateRegion(region_id, region), HttpStatus.OK);
	}
	
	/**
     * Elimina una región existente.
     *
     * @param region_id El identificador de la región a eliminar.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se elimina la región correctamente.
     */
	@DeleteMapping("/{region_id}")
	public ResponseEntity<ApiResponse> deleteRegion(@PathVariable int region_id){
		return new ResponseEntity<ApiResponse>(svc.deleteRegion(region_id), HttpStatus.OK);
	}
	
}
