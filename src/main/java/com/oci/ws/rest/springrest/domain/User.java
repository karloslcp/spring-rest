package com.oci.ws.rest.springrest.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oci.ws.rest.springrest.domain.validation.ExistingUserValidation;
import com.oci.ws.rest.springrest.domain.validation.NewUserValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @NotNull(groups = ExistingUserValidation.class)
    private Integer id;

    @NotBlank(groups = { NewUserValidation.class, ExistingUserValidation.class })
    private String name;

    @NotNull(groups = ExistingUserValidation.class)
    private LocalDate dateOfBirth;
}
