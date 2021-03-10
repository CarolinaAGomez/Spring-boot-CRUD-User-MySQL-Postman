package com.carolinafullstack.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Clave primaria
	private long id;
	
	@Column(length = 50)
	private String name;
	private String surname;
	private String mail;
	private Boolean enabled;
	
	public User() {}
	
	public User(long id, String name, String surname, String mail, Boolean enabled) {
		super();
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.enabled = enabled;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	
	

}
