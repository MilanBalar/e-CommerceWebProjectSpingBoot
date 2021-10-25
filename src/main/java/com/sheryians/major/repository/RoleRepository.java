package com.sheryians.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.entity.TblRoles;

public interface RoleRepository extends JpaRepository<TblRoles, Integer> {

}
