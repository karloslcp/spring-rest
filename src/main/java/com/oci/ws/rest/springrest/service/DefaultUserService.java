package com.oci.ws.rest.springrest.service;

import java.util.List;
import java.util.Optional;

import com.oci.ws.rest.springrest.domain.User;
import com.oci.ws.rest.springrest.repository.UserRepository;
import com.oci.ws.rest.springrest.service.exception.NoUserContentException;
import com.oci.ws.rest.springrest.service.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService
{
    private final static String NO_CONTENT_MESSAGE_PROPERTY = "users.exception.no-content";

    private final static String NOT_FOUND_MESSAGE_FORMAT_PROPERTY = "users.exception.not-found";

    private MessageSource messages;

    private UserRepository repository;

    @Override
    public List<User> getUsers()
    {
        List<User> users = repository.findAll();

        if (users.size() < 1)
        {
            String message = messages.getMessage(NO_CONTENT_MESSAGE_PROPERTY, null, LocaleContextHolder.getLocale());
            throw new NoUserContentException(message);
        }

        return users;
    }

    @Override
    public User getUser(final Integer id)
    {
        Optional<User> user = repository.findOne(id);
        if (user.isEmpty())
        {
            String message = messages.getMessage(NOT_FOUND_MESSAGE_FORMAT_PROPERTY, new Object[] { id }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        return null;
    }

    @Override
    public Integer createUser(final User user)
    {
        return repository.save(user);
    }

    @Override
    public int updateUser(final User user)
    {
        int usersUpdated = repository.update(user);
        if (usersUpdated == 0)
        {
            String message = messages.getMessage(NOT_FOUND_MESSAGE_FORMAT_PROPERTY, new Object[] { user.getId() }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        return usersUpdated;
    }

    @Override
    public int deleteUser(final Integer id)
    {
        int usersDeleted = repository.delete(id);
        if (usersDeleted == 0)
        {
            String message = messages.getMessage(NOT_FOUND_MESSAGE_FORMAT_PROPERTY, new Object[] { id }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        return usersDeleted;
    }
}
