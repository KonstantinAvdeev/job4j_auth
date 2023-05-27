package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public boolean update(Person person) {
        if (personRepository.findById(person.getId()).isPresent()) {
            this.personRepository.save(person);
            return true;
        }
        return false;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Optional<Person> findByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    public boolean delete(int id) {
        if (personRepository.findById(id).isPresent()) {
            return this.personRepository.deleteById(id);
        }
        return false;
    }

}
