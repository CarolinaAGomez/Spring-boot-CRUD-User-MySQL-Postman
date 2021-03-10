package com.carolinafullstack.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carolinafullstack.app.entity.User;
import com.carolinafullstack.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired  //Para relacion de dependencias.
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly =true) //SABE QUE NO HACE CAMBIOS EN LA BASE DE DATOS.
	public Iterable<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly =true)
	public Page<User> findAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly =true)
	public Optional<User> findById(long id) {
		
		return userRepository.findById(id);
	}

	@Override
	@Transactional //SABE QUE SOLO HACE CAMBIOS EN LA BASE DE DATOS.
	public User save(User user) {
		
		return userRepository.save(user);
	}

	@Override
	@Transactional //SABE QUE SOLO HACE CAMBIOS EN LA BASE DE DATOS.
	public void deleteById(long id) {
		
		userRepository.deleteById(id);
	}

}
