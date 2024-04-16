package com.crud.CRUD1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUD1.entities.User;
import com.crud.CRUD1.repo.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServicesImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
//		return user;
	}
	
	@Override
	public User findUserByEmail(String emailId){		
		return userRepo.findByEmail(emailId);	
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public User getUserById(int id) {
		User user=userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		return user;
	}
	
	@Override
	public User updateUser(int id, User user) {
		User updateUser=userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setEmailId(user.getEmailId());
		return userRepo.save(updateUser);
	}
	
	@Override
	public void delete(int id) {
		User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not exist with id: " + id));

        userRepo.delete(user);
	}
	
}
