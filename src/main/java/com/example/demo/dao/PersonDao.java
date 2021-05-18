package com.example.demo.dao;
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PersonDao {
    int insertPerson(UUID id, Person person); //insert person with an id

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID(); //allows to create UUID id ourselves, randomly generated
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople(); //contract for returning all people

    Optional<Person> selectPersonById(UUID id); //get by id

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
