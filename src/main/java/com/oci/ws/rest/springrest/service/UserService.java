package com.oci.ws.rest.springrest.service;

import java.util.List;

import com.oci.ws.rest.springrest.domain.User;

public interface UserService
{
    List<User> getAll();

    User getUser(Integer id);

    Integer createUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
