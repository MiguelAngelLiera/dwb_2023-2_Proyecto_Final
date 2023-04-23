package com.product.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

    @Query(value = "SELECT * FROM CATEGORY", nativeQuery = true)
    List<Category> findCategories();

    @Query(value = "SELECT * FROM CATEGORY WHERE STATUS = :status", nativeQuery = true)
    List<Category> findByStatus(@Param("status") Integer status);

    @Query(value = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = :category_id AND STATUS = 1", nativeQuery = true)
    Category findByCategoryId(@Param("category_id") Integer category_id);

    @Query(value = "SELECT * FROM CATEGORY WHERE CATEGORY = :category", nativeQuery = true)
    Category findByCategory(@Param("category") String category);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CATEGORY (CATEGORY,ACRONYM,STATUS) VALUES(:category,:acronym,1)", nativeQuery = true)
    void createCategory(@Param("category") String category, @Param("acronym") String acronym);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORY SET CATEGORY = :category WHERE CATEGORY_ID = :category_id", nativeQuery = true)
    Integer updateCategory(@Param("category_id") Integer category_id, @Param("category") String category);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORY SET STATUS= 1 WHERE CATEGORY_ID = :category_id", nativeQuery = true)
    void activateCategory(@Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORY SET STATUS= 0 WHERE CATEGORY_ID = :category_id", nativeQuery = true)
    void deleteByCategoryId(@Param("category_id") Integer category_id);
}
