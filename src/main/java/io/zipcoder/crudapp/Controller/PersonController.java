package io.zipcoder.crudapp.Controller;
import io.zipcoder.crudapp.Entity.Person;
import io.zipcoder.crudapp.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    public PersonController(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    @PostMapping("/people")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        try {
            Person retunedPerson = personRepository.save(person);
            return new ResponseEntity<String>("Person Created", HttpStatus.CREATED);
        }catch (Exception ex){
            return  new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {

        Person person = personRepository.findOne(id);
        return new ResponseEntity<Person>(person, HttpStatus.OK);

    }

    @GetMapping("/people/")
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> persons = (List<Person>) personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        Person person1 = personRepository.save(person);
        return new ResponseEntity<>("Updated Person", HttpStatus.OK);


    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> DeletePerson(@PathVariable long id) {
        try {
            personRepository.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}