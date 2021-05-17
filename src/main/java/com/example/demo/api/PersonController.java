package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/v1/person")  //this is the path for post request//endpoint
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired  //springboot injects service into constructor
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping //makes it an endpoint for post request
    public void addPerson(@RequestBody Person person){ //take request body, put inside person
        personService.addPerson(person);
    }

    @GetMapping //return all users from db
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }
}
