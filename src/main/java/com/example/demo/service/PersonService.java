package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //used for instantiating beans
public class PersonService {

    private final PersonDao personDao; //refernce to it, dependecy injection

    @Autowired //injects into actual constructor
    public PersonService(@Qualifier("postgres") PersonDao personDao) { //bean us connected to fake db
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){ //returning all the poeple
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id,Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }

}
