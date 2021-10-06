package com.sheryians.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.entity.TblCategories;

public interface CategoryRepository extends JpaRepository<TblCategories, Integer> {

}
