package com.oci.ws.rest.springrest.service;

import java.util.List;

import static java.lang.String.format;

import com.oci.ws.rest.springrest.domain.User;
import com.oci.ws.rest.springrest.repository.UserRepository;
import com.oci.ws.rest.springrest.service.exception.NoUsersContentException;
import com.oci.ws.rest.springrest.service.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService
{
    private final static String USER_NOT_FOUND_MESSAGE_FORMAT = "User with id [%s] was not found.";

    private UserRepository repository;

    @Override
    public List<User> getAll()
    {
        List<User> users = repository.findAll();
        if (users.size() < 1)
        {
            throw new NoUsersContentException("No users were found.");
        }
        return users;
    }

    @Override
    public User getUser(final Integer id)
    {
        User user = repository.findOne(id);
        if (user == null)
        {
            throw new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE_FORMAT, id));
        }
        return user;
    }

    @Override
    public Integer createUser(final User user)
    {
        return repository.save(user).getId();
    }

    @Override
    public int updateUser(final User user)
    {
        int updatedUsers = repository.update(user);
        if (updatedUsers == 0)
        {
            throw new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE_FORMAT, user.getId()));
        }
        return updatedUsers;
    }

    @Override
    public int deleteUser(final Integer id)
    {
        int deletedUsers = repository.delete(id);
        if (deletedUsers == 0)
        {
            throw new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE_FORMAT, id));
        }
        return deletedUsers;
    }
}
