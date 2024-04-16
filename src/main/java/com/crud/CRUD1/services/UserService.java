package com.crud.CRUD1.services;

import com.crud.CRUD1.entities.User;
import java.util.List;

public interface UserService {
	public User saveUser(User user);
	public List<User> getAllUsers();
	public User findUserByEmail(String emailId);
	public User getUserById(int id);
	public User updateUser(int id, User user);
	public void delete(int id);
}
