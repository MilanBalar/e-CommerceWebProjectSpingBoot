package com.sheryians.major.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.entity.TblUser;


public interface UserRepository extends JpaRepository<TblUser, Integer>{

	Optional<TblUser> findTblUserByEmail(String email);

}
