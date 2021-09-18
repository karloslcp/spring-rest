package com.oci.ws.rest.springrest.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private Integer id;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Name attribute is missing")
    @Size(min = 2)
    private String name;
}
