package com.oci.ws.rest.springrest.controller;

import java.net.URI;
import java.util.List;

import com.oci.ws.rest.springrest.controller.exception.UserNotFoundException;
import com.oci.ws.rest.springrest.model.User;
import com.oci.ws.rest.springrest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController
{
    private UserRepository repository;

    @GetMapping
    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id)
    {
        User user = repository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with ud: %d was not found.", id));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }
}
