package com.crud.CRUD1.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.crud.CRUD1.entities.User;
//import com.crud.CRUD1.repo.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Given
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailId("john.doe@example.com");
        userRepository.save(user);

        // When
        User foundUser = userRepository.findByEmail("john.doe@example.com");

        // Then
        assertNotNull(foundUser);
        assertEquals(user.getFirstName(), foundUser.getFirstName());
        assertEquals(user.getLastName(), foundUser.getLastName());
        assertEquals(user.getEmailId(), foundUser.getEmailId());
    }
}

