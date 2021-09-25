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
    private MessageSource messages;

    private UserRepository repository;

    @Override
    public List<User> get()
    {
        List<User> users = repository.findAll();
        if (users.isEmpty())
        {
            throw new NoUserContentException();
        }
        return users;
    }

    @Override
    public User get(final Integer id)
    {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isEmpty())
        {
            String message = messages.getMessage("user.exception.not-found", new Object[] { id }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        return userOptional.get();
    }

    @Override
    public Integer add(final User user)
    {
        final User createdUser = repository.saveAndFlush(user);
        return createdUser.getId();
    }

    @Override
    public void update(final User user)
    {
        if (!repository.existsById(user.getId()))
        {
            String message = messages.getMessage("user.exception.not-found", new Object[] { user.getId() }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        repository.saveAndFlush(user);
    }

    @Override
    public void delete(final Integer id)
    {
        if (!repository.existsById(id))
        {

            final var message = messages.getMessage("user.exception.not-found", new Object[] { id }, LocaleContextHolder.getLocale());
            throw new UserNotFoundException(message);
        }
        repository.deleteById(id);
    }
}
