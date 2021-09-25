package com.oci.ws.rest.springrest.resource;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.oci.ws.rest.springrest.domain.User;
import com.oci.ws.rest.springrest.domain.validation.ExistingUserValidation;
import com.oci.ws.rest.springrest.domain.validation.NewUserValidation;
import com.oci.ws.rest.springrest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping(produces = "application/xml")
    public List<User> getAllUsers()
    {
        return service.get();
    }

    @GetMapping("{id}")
    public EntityModel<User> getUser(@PathVariable Integer id)
    {
        final Link allUsersLink = linkTo(methodOn(this.getClass()).getAllUsers()).withRel("all-users");
        final User user = service.get(id);
        return EntityModel.of(user, allUsersLink);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Validated(NewUserValidation.class) @RequestBody User user)
    {
        final Integer newUserId = service.add(user);
        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(newUserId)
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public void updateUser(@Validated(ExistingUserValidation.class) @RequestBody User user)
    {
        service.update(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        service.delete(id);
    }
}
