package com.product.api.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.exception.ApiException;

@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	RepoProduct repo;
	
	@Autowired
	RepoCategory repoCategory;

	@Override
	public Product getProduct(String gtin) {
		Product product = repo.findByGtin(gtin); // sustituir null por la llamada al método implementado en el repositorio
		if (product != null) {
			product.setCategory(repoCategory.findByCategoryId(product.getCategory_id()));
			return product;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
	}

	/*
	 * 4. Implementar el método createProduct considerando las siguientes validaciones:
  		1. validar que la categoría del nuevo producto exista
  		2. el código GTIN y el nombre del producto son únicos
  		3. si al intentar realizar un nuevo registro ya existe un producto con el mismo GTIN pero tiene estatus 0, 
  		   entonces se debe cambiar el estatus del producto existente a 1 y actualizar sus datos con los del nuevo registro
	 */
	@Override
	public ApiResponse createProduct(Product in) {
		Product pAlmacenado = repo.findByProduct(in.getProduct());
		Category cProducto = repoCategory.findByCategoryId(in.getCategory_id());

        if(cProducto != null){
			if(pAlmacenado != null){
				if(pAlmacenado.getStatus() == 0){
					repo.updateProduct(pAlmacenado.getProduct_id(), in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
					return new ApiResponse("product activated");
				}
                throw new ApiException(HttpStatus.BAD_REQUEST,"product name already exist");
			}
            else{
				pAlmacenado = repo.findByGtin(in.getGtin());
				if(pAlmacenado != null)
					throw new ApiException(HttpStatus.BAD_REQUEST,"product gtin already exist");
				repo.createProduct(in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
				return new ApiResponse("product created");
            }
		}
		throw new ApiException(HttpStatus.NOT_FOUND,"category not found");
	}

	@Override
	public ApiResponse updateProduct(Product in, Integer id) {
		Integer updated = 0;
		try {
			updated = repo.updateProduct(id, in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exist");
			if (e.getLocalizedMessage().contains("product"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exist");
			if (e.contains(SQLIntegrityConstraintViolationException.class))
				throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		if(updated == 0)
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be updated");
		else
			return new ApiResponse("product updated");
	}

	@Override
	public ApiResponse deleteProduct(Integer id) {
		if (repo.deleteProduct(id) > 0)
			return new ApiResponse("product removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be deleted");
	}

	@Override
	public ApiResponse updateProductStock(String gtin, Integer stock) {
		Product product = getProduct(gtin);
		if(stock > product.getStock())
			throw new ApiException(HttpStatus.BAD_REQUEST, "stock to update is invalid");
		
		repo.updateProductStock(gtin, product.getStock() - stock);
		return new ApiResponse("product stock updated");
	}

	@Override
	public List<Product> getListProducts(Integer category_id) {
		// TODO Auto-generated method stub
		return repo.findByCategory(category_id);
		//throw new UnsupportedOperationException("Unimplemented method 'getListProducts'");
	}

	@Override
	public ApiResponse updateProductCategory(String gtin, int category_id) {
		// TODO Auto-generated method stub
		Product product = getProduct(gtin);
		if(product == null){
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
		}
		
		if(repoCategory.findByCategoryId(category_id) ==  null){
			throw new ApiException(HttpStatus.NOT_FOUND, "category not found");
		}
		
		repo.updateProductCategory(gtin, category_id);
		return new ApiResponse("product category updated");
		//throw new UnsupportedOperationException("Unimplemented method 'updateProductCategory'");
	}
}