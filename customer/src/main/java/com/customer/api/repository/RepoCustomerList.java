package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.api.dto.DtoCustomerList;

/**
     * Busca y devuelve una lista de clientes según el estado especificado.
     *
     * @param status El estado de los clientes a buscar.
     * @return Una lista de objetos DtoCustomerList que corresponden al estado especificado.
     */
@Repository
public interface RepoCustomerList  extends JpaRepository<DtoCustomerList, Integer>{

	List<DtoCustomerList> findByStatus(Integer status);

}
