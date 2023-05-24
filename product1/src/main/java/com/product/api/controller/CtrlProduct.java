package com.product.api.controller;

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

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.exception.ApiException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;

	// 1. Implementar método getProduct
	@GetMapping("/product/{gtin}")
    public ResponseEntity<Product> getCategory(@NotNull @PathVariable("gtin") String gtin){
        return new ResponseEntity<>(svc.getProduct(gtin), HttpStatus.OK);
    }

	//P7. Endpoint List Products
	@GetMapping("/product/category/{category_id}")
    public ResponseEntity<List<Product>> getListProducts(@PathVariable Integer category_id){
        return new ResponseEntity<>(svc.getListProducts(category_id), HttpStatus.OK);
    }

	@PostMapping("/product")
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createProduct(in),HttpStatus.OK);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@NotNull @PathVariable("id") Integer id, @Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProduct(in, id),HttpStatus.OK);
	}

	// 2. Implementar método updateProductStock
	@PutMapping("/product/{gtin}/stock/{stock}")
	public ResponseEntity<ApiResponse> updateProductSock(@PathVariable("gtin") String gtin, @PathVariable("stock") Integer stock, @Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProductStock(gtin, stock),HttpStatus.OK);
	}

	//P7.
	@PutMapping("/product/{gtin}/category")
	public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable("gtin") String gtin, @Valid @RequestBody  int category_id, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProductCategory(gtin, category_id),HttpStatus.OK);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteProduct(id), HttpStatus.OK);
	}
}
