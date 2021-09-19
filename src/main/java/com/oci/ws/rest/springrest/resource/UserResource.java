package com.oci.ws.rest.springrest.resource;

import java.util.List;

import com.oci.ws.rest.springrest.domain.User;
import com.oci.ws.rest.springrest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserResource
{
    private UserService service;

    @GetMapping
    public List<User> getAllUsers()
    {
        return service.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id)
    {
        return service.getUser(id);
    }
}
