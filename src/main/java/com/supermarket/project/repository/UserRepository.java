package com.supermarket.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.project.model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
	
	Boolean existsByEmail(String email);
}
