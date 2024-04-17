package com.crud.CRUD1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.CRUD1.entities.User;
import com.crud.CRUD1.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		
		User existingUser = userService.findUserByEmail(user.getEmailId());
			
	    if(existingUser!=null)	
		{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with the same email already exists");
        }
        // If not, proceed to save the user
        User addedUser = userService.saveUser(user);
        return ResponseEntity.ok(addedUser);
    }
	
	@GetMapping
	public List<User> get(){
		return userService.getAllUsers();
	}
	
	@GetMapping("{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable int id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
	
	@PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody User user) {
        User updateUser = userService.findUserByEmail(user.getEmailId());
        
        if(updateUser!=null) {
        	return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with the same email already exists");
        	
        }
        User updatedUser=userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
