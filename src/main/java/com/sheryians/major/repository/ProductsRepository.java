package com.sheryians.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sheryians.major.entity.TblProducts;

public interface ProductsRepository extends JpaRepository<TblProducts, Long> {
	@Query("select u from TblProducts u where u.tblCategories.categoryId=:categoryId")
	List<TblProducts> getAllProductByCaterogyId(@Param("categoryId") int categoryId);

}
