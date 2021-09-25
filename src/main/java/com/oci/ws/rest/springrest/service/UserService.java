package com.oci.ws.rest.springrest.service;

import java.util.List;

import com.oci.ws.rest.springrest.domain.User;

public interface UserService
{
    List<User> get();

    User get(Integer id);

    Integer add(User user);

    void update(User user);

    void delete(Integer id);
}
