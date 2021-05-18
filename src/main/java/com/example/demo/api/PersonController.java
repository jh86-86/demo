package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")  //this is the path for request//endpoint
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired  //springboot injects service into constructor
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping //makes it an endpoint for post request
    public void addPerson(@Valid @Validated @NotNull @RequestBody Person person){ //take request body, put inside person
        personService.addPerson(person); //not null will fail there rather than in our code
    }

    @GetMapping //return all users from db
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path= "{id}") //allows me to have forward slash for the id
    public Person getPersonId(@PathVariable("id") UUID id){  //turns id into a UUID pathvariable connects to the path varirable mapping abve
        return personService.getPersonById(id)
                .orElse(null); //could have custom message here could add 404
    }

    @DeleteMapping(path="{id}") //pass down to service
    public void deletePersonById(@PathVariable("id")UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Person personToUpdate){ //takes a person from the request bdy
        personService.updatePerson(id, personToUpdate);
    }

}
