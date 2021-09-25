package com.oci.ws.rest.springrest.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.oci.ws.rest.springrest.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
{
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(1, "Karlos", LocalDate.of(1985, 2, 24)));
        users.add(new User(2, "Claudia", LocalDate.of(1988, 8, 24)));
        users.add(new User(3, "Susana", LocalDate.of(1998, 11, 2)));
    }
    public List<User> findAll()
    {
        return users;
    }

    public Optional<User> findOne(Integer id)
    {
        return users
            .stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
    }

    public Integer addOne(User user)
    {
        Integer lastId = users.stream().mapToInt(User::getId).max().orElse(0);

        user.setId(++lastId);
        users.add(user);

        return lastId;
    }

    public int updateOne(User user)
    {
        final AtomicInteger usersUpdated = new AtomicInteger();

        findOne(user.getId()).ifPresent(u -> {
            u.setName(user.getName());
            u.setDateOfBirth(user.getDateOfBirth());
            usersUpdated.getAndIncrement();
        });

        return usersUpdated.get();
    }

    public int deleteOne(Integer id)
    {
        int usersDeleted = 0;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext())
        {
            User user = iterator.next();
            if (user.getId().equals(id))
            {
                iterator.remove();
                usersDeleted++;
            }
        }

        return usersDeleted;
    }
}
