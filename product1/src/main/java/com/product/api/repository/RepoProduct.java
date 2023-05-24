package com.product.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer>{

	// 3. Implementar la firma de un método que permita consultar un producto por su código GTIN y con estatus 1
	@Query(value = "SELECT * FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.category_id = CATEGORY.category_id "
		+ "WHERE PRODUCT.gtin = :gtin AND PRODUCT.STATUS = 1", nativeQuery = true)
    Product findByGtin(@Param("gtin") String gtin);

	@Query(value = "SELECT * FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.category_id = CATEGORY.category_id "
		+ "WHERE product = :product", nativeQuery = true)
    Product findByProduct(@Param("product") String product);

	Product findByGtinAndStatus(String gtin, Integer status);

	Product findByProductAndStatus(String product, Integer status);

	@Query(value="SELECT * FROM product WHERE (product = :productName OR gtin = :gtin) AND category_id = :categoryId", nativeQuery = true )
	Product findProduct(String gtin, String productName, Integer categoryId);

	//P7
	@Query(value = "SELECT * FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.category_id = CATEGORY.category_id "
		+ "WHERE CATEGORY.category_id = :category_id", nativeQuery = true)
    List<Product> findByCategory(@Param("category_id") Integer category_id);

	@Modifying
	@Transactional
	@Query(value ="UPDATE PRODUCT "
					+ "SET gtin = :gtin, "
						+ "product = :product, "
						+ "description = :description, "
						+ "price = :price, "
						+ "stock = :stock, "
						+ "status = 1, "
						+ "category_id = :category_id "
					+ "WHERE product_id = :product_id", nativeQuery = true)
	Integer updateProduct(
			@Param("product_id") Integer product_id,
			@Param("gtin") String gtin,
			@Param("product") String product,
			@Param("description") String description,
			@Param("price") Double price,
			@Param("stock") Integer stock,
			@Param("category_id") Integer category_id
		);

	@Modifying
	@Transactional
	@Query(value ="UPDATE PRODUCT SET status = 0 WHERE product_id = :product_id AND status = 1", nativeQuery = true)
	Integer deleteProduct(@Param("product_id") Integer product_id);

	/*@Modifying
    @Transactional
    @Query(value = "UPDATE PRODUCT SET STATUS= 1 WHERE PRODUCT_ID = :product_id", nativeQuery = true)
    void activateProduct(@Param("category_id") Integer product_id);*/

	@Modifying
	@Transactional
	@Query(value ="UPDATE PRODUCT SET stock = :stock WHERE gtin = :gtin AND status = 1", nativeQuery = true)
	Integer updateProductStock(@Param("gtin") String gtin, @Param("stock") Integer stock);

	/**
	 * Crea un nuevo producto
	 * @param category
	 * @param acronym
	 */
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUCT (GTIN,PRODUCT,DESCRIPTION,PRICE,STOCK,CATEGORY_ID,STATUS) VALUES(:gtin,:product,:description,:price,:stock,:category_id,1)", nativeQuery = true)
    void createProduct(@Param("gtin") String gtin, @Param("product") String product, @Param("description") String description, @Param("price") double price, @Param("stock") Integer stock, @Param("category_id") Integer category_id);

	@Modifying
	@Transactional
	@Query(value ="UPDATE PRODUCT SET category_id = :category_id WHERE gtin = :gtin AND status = 1", nativeQuery = true)
	Integer updateProductCategory(@Param("gtin") String gtin, @Param("category_id") Integer category_id);

	//INSERT INTO PRODUCT (GTIN,PRODUCT,DESCRIPTION,PRICE,STOCK,CATEGORY_ID,STATUS) VALUES("564554","Paracetamol","medicamento",45.00,49,1,1);
}
