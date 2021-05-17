package com.example.demo.dao;
import com.example.demo.model.Person;
import java.util.UUID;


public interface PersonDao {
    int insertPerson(UUID id, Person person); //insert person with an id

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID(); //allows to create UUID id ourselves, randomly generated
        return insertPerson(id, person);
    }

}
