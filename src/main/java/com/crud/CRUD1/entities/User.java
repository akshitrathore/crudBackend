package com.crud.CRUD1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
    private int id;
    private String firstname;
    private String lastname;
    private String emailId;
    
    public User(){
    	
    }
    
    public User (int id, String firstname, String lastname, String emailId){
    	this.id=id;
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.emailId=emailId;
    }
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id=id;
    }
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
    
}
