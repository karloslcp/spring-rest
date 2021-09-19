package com.oci.ws.rest.springrest.resource;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.oci.ws.rest.springrest.domain.User;
import com.oci.ws.rest.springrest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserResource
{
    private UserService service;

    @GetMapping
    public List<User> getAllUsers()
    {
        return service.getAll();
    }

    @GetMapping("{id}")
    public EntityModel<User> getUser(@PathVariable Integer id)
    {
        Link linkToAllUsers = linkTo(methodOn(this.getClass()).getAllUsers()).withRel("all-users");
        User user = service.getUser(id);
        return EntityModel.of(user, linkToAllUsers);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
    {
        Integer newUserId = service.createUser(user);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(newUserId)
            .toUri();
        return ResponseEntity.created(location).build();
    }

    // void returns http status 200 if not exception is thrown
    @PutMapping("{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id)
    {
        user.setId(id);
        service.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        service.deleteUser(id);
    }
}
