package com.oci.ws.rest.springrest.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oci.ws.rest.springrest.domain.validation.ExistingUserValidation;
import com.oci.ws.rest.springrest.domain.validation.NewUserValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ExistingUserValidation.class)
    private Integer id;

    @NotBlank(groups = { NewUserValidation.class, ExistingUserValidation.class })
    private String name;

    @NotNull(groups = ExistingUserValidation.class)
    private LocalDate dateOfBirth;
}
