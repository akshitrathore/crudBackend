package com.crud.CRUD1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.CRUD1.entities.User;
import com.crud.CRUD1.repo.UserRepository;

@SpringBootApplication
public class Crud1Application implements CommandLineRunner{
	

	public static void main(String[] args) {
		SpringApplication.run(Crud1Application.class, args);
		
	}

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		User user1 = new User();
//		user1.setFirstName("Akshit");
//		user1.setLastName("Rathore");
//		user1.setEmailId("ar@gmail.com");
//		userRepo.save(user1);
//		
//		User user2 = new User();
//		user2.setFirstName("Ashutosh");
//		user2.setLastName("Pant");
//		user2.setEmailId("ap@gmail.com");
//		userRepo.save(user2);

	}

}
