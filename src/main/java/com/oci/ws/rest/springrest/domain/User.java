package com.oci.ws.rest.springrest.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    private Integer id;

    private LocalDate dateOfBirth;

    private String name;
}
