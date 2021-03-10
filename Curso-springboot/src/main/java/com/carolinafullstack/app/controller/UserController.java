package com.carolinafullstack.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carolinafullstack.app.entity.User;
import com.carolinafullstack.app.service.UserService;

@RestController //Combinacion de rest con controller que maneja hhtp
@RequestMapping("/api/users") //como es el nombre del link
public class UserController {

@Autowired
private UserService userService;

//Post es para crear. ResponseEntity<?> Respuesta de entidad
//de usuario. El metodo se llama create y recibe un usuario
//@RequestBody se recibe en el cuerpo de la peticion un usuario

//create a new user
@PostMapping
public ResponseEntity<?> create (@RequestBody User user){

	//Devuelve un codigo de creado que es el 201 cuando le entrega el cuerpo de un user guardado.
return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
}

@GetMapping("/{id}")
//getmapping es para leer el ususario, se le pasa un id y como cambia va entre llaves
// Si la variable se escribe igual que la variable que recibe no hace falta el value (@PathVariable Long UserId)
public ResponseEntity<?> read (@PathVariable(value = "id") long userId) {

	// Se contruye un opcional de user y se guarda en oUser lo que trae. Trae un user.
Optional<User> oUser= userService.findById(userId);
//Si no esta presente retorna una respuesta con el codigo 404
if(!oUser.isPresent()) {
	
	return ResponseEntity.notFound().build();
}

return ResponseEntity.ok(oUser);
}


@PutMapping ("/{id}")
public ResponseEntity<?> update (@RequestBody User UserDetails,@PathVariable (value = "id") long userId){

	Optional<User> oUser = userService.findById(userId);
	if (!oUser.isPresent()) {
		return ResponseEntity.notFound().build();
	}
	
	//Esta seria una forma alternativa de modificar los datos pero tambien modificaria el id. Coloca el userdatails en el objeto oUser.
	//BeanUtils.copyProperties(UserDetails, oUser.get());
	oUser.get().setName(UserDetails.getName());
	oUser.get().setSurname(UserDetails.getSurname());
	oUser.get().setMail(UserDetails.getMail());
	oUser.get().setEnabled(UserDetails.getEnabled());
	// Que guaede el Ouser que es el que trajo y modifico. por eso se pone ouser.get para que lo traiga y lo guarde.
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> delete (@PathVariable long id){
	
	// Se puede hacer con el optional tmb.
	if (!userService.findById(id).isPresent()) {
		return ResponseEntity.notFound().build();
	}
	userService.deleteById(id);
	return ResponseEntity.ok().build();
}


//Mostrar la lista de usuarios.

@GetMapping
public List<User> ReadAll(){
	// Utiliza un objetos que llamda a los metodos.
	//1 para recorrer el iterable y el collector para pasarlo a una lista. Elflase es para pasarlo a secuencial y no paralelo
	List<User> users = StreamSupport
			.stream(userService.findAll().spliterator(),false)
			.collect(Collectors.toList());
	
	return users;
}
}

