package com.oci.ws.rest.springrest.repository;

import com.oci.ws.rest.springrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
}
