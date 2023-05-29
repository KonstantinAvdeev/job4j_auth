package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;
    private BCryptPasswordEncoder encoder;

    public PersonController(final PersonService persons, BCryptPasswordEncoder encoder) {
        this.persons = persons;
        this.encoder = encoder;
    }

    @GetMapping({"/", "/all"})
    public List<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        return new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        if (this.persons.update(person)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (this.persons.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}