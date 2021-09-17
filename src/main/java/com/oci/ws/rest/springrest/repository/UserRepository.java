package com.oci.ws.rest.springrest.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.oci.ws.rest.springrest.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
{
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(1, "Karlos", LocalDate.of(1985, 2, 24)));
        users.add(new User(2, "Claudia", LocalDate.of(1988, 8, 24)));
        users.add(new User(3, "Susy", LocalDate.of(1998, 11, 2)));
    }
    public List<User> findAll()
    {
        return users;
    }

    public User save(User user)
    {
        Integer lastId = users.stream().map(User::getId).max((i1, i2) -> i1 > i2 ? i1 : i2).orElse(1);
        user.setId(lastId + 1);
        users.add(user);
        return user;
    }

    public User findOne(Integer id)
    {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
