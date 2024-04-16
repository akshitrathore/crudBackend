package com.crud.CRUD1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crud.CRUD1.entities.User;
import com.crud.CRUD1.repo.UserRepository;

public class UserServicesImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServicesImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Akshit");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }
    
    @Test
    public void testFindUserByEmail_UserExists() {
        User user = new User();
        user.setEmailId("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User result = userService.findUserByEmail("test@example.com");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testFindUserByEmail_UserNotExist() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        User result = userService.findUserByEmail("nonexistent@example.com");

        assertEquals(null, result);
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }
    
    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Akshit", "Rathore", "ar@example.com"));
        userList.add(new User(2, "Ashutosh", "Pant", "ap@example.com"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(userList, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        User user = new User(1, "Akshit", "Rathore", "ar@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(1);
        });
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1, "Akshit", "Rathore", "ar@example.com");
        User updatedUser = new User(1, "Akshit", "Rathore", "updated@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);

        User result = userService.updateUser(1, updatedUser);

        assertEquals(updatedUser, result);
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        User user = new User(1, "Akshit", "Rathore", "ar@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.delete(1);

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.delete(1);
        });
    }
}
