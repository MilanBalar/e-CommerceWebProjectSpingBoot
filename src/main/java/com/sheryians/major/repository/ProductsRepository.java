package com.sheryians.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.entity.TblProducts;

public interface ProductsRepository extends JpaRepository<TblProducts, Long> {

}
