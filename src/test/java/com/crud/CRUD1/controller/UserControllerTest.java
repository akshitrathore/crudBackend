package com.crud.CRUD1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crud.CRUD1.entities.User;
import com.crud.CRUD1.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setEmailId("test@example.com");

        when(userService.findUserByEmail("test@example.com")).thenReturn(null);
        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).findUserByEmail("test@example.com");
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testCreateUser_Conflict() {
        User user = new User();
        user.setEmailId("existing@example.com");

        when(userService.findUserByEmail("existing@example.com")).thenReturn(new User());

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User with the same email already exists", response.getBody());
        verify(userService, times(1)).findUserByEmail("existing@example.com");
        verify(userService, never()).saveUser(user); // Ensure saveUser is not called
    }


    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Akshit", "Rathore", "ar@example.com"));
        userList.add(new User(2, "Ashutosh", "Pant", "ap@example.com"));

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> result = userController.get();

        assertEquals(userList, result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        User user = new User(1, "Akshit", "Rathore", "ar@example.com");

        when(userService.getUserById(1)).thenReturn(user);

        ResponseEntity<User> result = userController.getEmployeeById(1);

        assertEquals(user, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).getUserById(1);
    }

//    @Test
//    public void testUpdateUser() {
//        User user = new User(1, "Akshit", "Rathore", "ar@example.com");
//        User updatedUser = new User(1, "Akshit", "Rathore", "updated@example.com");
//
//        when(userService.updateUser(1, updatedUser)).thenReturn(updatedUser);
//
//        ResponseEntity<User> result = userController.update(1, updatedUser);
//
//        assertEquals(updatedUser, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        verify(userService, times(1)).updateUser(1, updatedUser);
//    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<HttpStatus> result = userController.deleteEmployee(1);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(userService, times(1)).delete(1);
    }
}
