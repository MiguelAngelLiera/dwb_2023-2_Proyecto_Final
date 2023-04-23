package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory{

    @Autowired
    RepoCategory repo;

    @Override
    public List<Category> getCategories() {
        // TODO Auto-generated method stub

        //throw new UnsupportedOperationException("Unimplemented method 'getCategories'");
        return repo.findByStatus(1);
    }

    @Override
    public Category getCategory(Integer category_id) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
        Category category = repo.findByCategoryId(category_id);
        if(category == null){
            throw new ApiException(HttpStatus.BAD_REQUEST, "category does not exist");
        }else{
            return category;
        }
    }

    @Override
    public ApiResponse createCategory(Category category) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'createCategory'");
        Category guardada = repo.findByCategory(category.getCategory());
        if(guardada != null){
            if(guardada.getStatus() == 0){
                repo.activateCategory(guardada.getCategory_id());
                return new ApiResponse("category has been activated");
            }
            else{
                throw new ApiException(HttpStatus.BAD_REQUEST,"La Categoria ya existe");
            }
                
        }
        repo.createCategory(category.getCategory(),category.getAcronym());
        return new ApiResponse("Categoria creada");
    }

    @Override
    public ApiResponse updateCategory(Integer category_id, Category category) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'createCategory'");
        Category guardada = repo.findByCategoryId(category_id);
        if (guardada == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Categoria no existe");
        }else{
            if(guardada.getStatus() == 0)
                throw new ApiException(HttpStatus.BAD_REQUEST,"La Categoria no esta activa");
            else{
                guardada = repo.findByCategory(category.getCategory());
                if(guardada != null)
                    throw new ApiException(HttpStatus.BAD_REQUEST,"La categoria ya existe");
                repo.updateCategory(category_id, category.getCategory());
                return new ApiResponse("Categoria actualizada");
            }
        }
        
        //repo.createCategory(category.getCategory());
        //return "Region creada";
    }

    @Override
    public ApiResponse deleteCategory(Integer category_id) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
        Category guardada = repo.findByCategoryId(category_id);
        if (guardada == null) {
            throw new ApiException(HttpStatus.NOT_FOUND,"Categoria no existe");
        }else{
            repo.deleteByCategoryId(category_id);
            return new ApiResponse("Categoria fue eliminada");
        }
    }
    
}
