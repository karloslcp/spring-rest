package com.oci.ws.rest.springrest.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.oci.ws.rest.springrest.resource.validation.CreateUserInformation;
import com.oci.ws.rest.springrest.resource.validation.UpdateUserInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @NotNull(groups = UpdateUserInformation.class)
    private Integer id;

    @Past(groups = { CreateUserInformation.class, UpdateUserInformation.class })
    private LocalDate dateOfBirth;

    @NotBlank(groups = { CreateUserInformation.class, UpdateUserInformation.class })
    private String name;
}
