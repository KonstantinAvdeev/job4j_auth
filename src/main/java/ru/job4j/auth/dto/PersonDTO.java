package ru.job4j.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO {
    @Size(min = 6, message = "Password must contains at least 6 symbols")
    private String password;
}
