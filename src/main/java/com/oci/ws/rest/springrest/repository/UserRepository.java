package com.oci.ws.rest.springrest.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.oci.ws.rest.springrest.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
{
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(1, LocalDate.of(1985, 2, 24), "Karlos"));
        users.add(new User(2, LocalDate.of(1988, 8, 24), "Claudia"));
        users.add(new User(3, LocalDate.of(1998, 11, 2), "Susy"));
    }
    //find all
    public List<User> findAll()
    {
        return users;
    }

    //find one
    public Optional<User> findOne(Integer id)
    {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    //create one
    public int save(User user)
    {
        int lastId = users.stream().mapToInt(User::getId).max().orElse(0);
        user.setId(++lastId);
        users.add(user);
        return lastId;
    }

    //update one
    public int update(User user)
    {
        int counter = 0;
        for (User u : users)
        {
            if (u.getId().equals(user.getId()))
            {
                u.setName(user.getName());
                u.setDateOfBirth(user.getDateOfBirth());
                counter++;
            }
        }
        return counter;
    }

    //delete one
    public int delete(Integer id)
    {
        int counter = 0;
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext())
        {
            User user = iterator.next();
            if (user.getId().equals(id))
            {
                iterator.remove();
                counter++;
            }
        }
        return counter;
    }
}
