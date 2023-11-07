package com.example.restdemo.Controller;
import com.example.restdemo.Repository.PersonRepository;
import com.example.restdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class MainController {

    //server.port=8070

    @Autowired
    private PersonRepository repository;
    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }
    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        if (status == HttpStatus.OK){
            person.setId(id);
            return new ResponseEntity(repository.save(person), status);
        }
        else
            return new ResponseEntity(repository.save(person), status);
    }
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}
