package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    //constructor for above
    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }//use this over  the fake list


    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql ="INSERT INTO person (id , name) VALUES (?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                person.getName()
        );
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
            return jdbcTemplate.query(sql, (resultSet, i) -> {  //Rowmapper lambda has access to result set and index
            UUID id = UUID.fromString(resultSet.getString("id")); //this will convert the string here to UUID
            String name = resultSet.getString("name");  //gets the name
            return new Person(id, name);
        });

//        return List.of(new Person(UUID.randomUUID(), "From Postgres DB"));    my fake list
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";


        Person person= jdbcTemplate.queryForObject(
               sql,
               (resultSet, i) ->{
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name= resultSet.getString("name");
                    return new Person(personId, name);
        }, id);
       return Optional.ofNullable(person); //might not find student with given id so it can be nullable
    }

    @Override
    public int deletePersonById(UUID id) {

        final String sql = "DELETE FROM person WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql= "UPDATE person SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, person.getName(), id);

    }
}
