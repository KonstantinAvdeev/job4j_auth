package ru.job4j.auth.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    /*
    Аннотация @Email не подошла, т.к. пропускает формат K12ffdsf@mail (без окончания)
     */
    @Pattern(regexp = "\\w+@\\w+\\.(ru|com|org)", message = "Please use a pattern *@*.ru, "
            + "where * = any number of symbols like _ , numbers or characters(a-z), and 'ru' "
            + "can be replaced by 'org' or 'com'")
    private String login;
    @Size(min = 6, message = "Password must contains at least 6 symbols")
    private String password;
}