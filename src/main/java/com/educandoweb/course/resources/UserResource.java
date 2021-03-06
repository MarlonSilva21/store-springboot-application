package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> listUsers = userService.findAll();

		return ResponseEntity.ok().body(listUsers);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable long id) {

		return ResponseEntity.ok().body(userService.findById(id));
	}

	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody User user) {
		User obj = userService.insertUser(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user = userService.updateUser(id, user);
		
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleleUser(@PathVariable Long id) {
		userService.deleteUSer(id);

		return ResponseEntity.noContent().build();
	}

}
