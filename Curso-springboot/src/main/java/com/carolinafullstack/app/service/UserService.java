package com.carolinafullstack.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carolinafullstack.app.entity.User;

public interface UserService {
	
	public Iterable <User> findAll();
	
	public Page<User> findAll(Pageable pageable);// Se puede usar paginacion--Ver que es?
	public Optional<User> findById(long id);
	
	public User save(User user);// Devuelve un usuario
	
	public void deleteById(long id);

}
