package com.crud.CRUD1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.CRUD1.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
	@Query(value = "SELECT * FROM users WHERE email_Id = ?1", nativeQuery = true)
	User findByEmail(@Param("emailId") String emailId);
}