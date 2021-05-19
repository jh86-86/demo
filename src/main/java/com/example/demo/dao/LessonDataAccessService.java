package com.example.demo.dao;

import com.example.demo.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("po")
public class LessonDataAccessService implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    //constructor for above
    @Autowired
    public LessonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertLesson(UUID id, Lesson lesson) {
        final String sql ="INSERT INTO lesson (id , name, youtubeLink, tagline) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                lesson.getName(),
                lesson.getYoutubeLink(),
                lesson.getTagLine()
        );
    }

    @Override
    public List<Lesson> selectAllLessons() {
        final String sql = "SELECT id, name, youtubeLink, tagline FROM Lesson";
       return jdbcTemplate.query(sql, (resultSet, i) -> {  //Rowmapper lambda has access to result set and index
            UUID id = UUID.fromString(resultSet.getString("id")); //this will convert the string here to UUID
            String name = resultSet.getString("name");
            String youtubeLink = resultSet.getString("youtubeLink");
            String tagLine = resultSet.getString("tagline");
            return new Lesson(id, name, youtubeLink, tagLine);
        });

    }

    @Override
    public Optional<Lesson> selectLessonById(UUID id) {
        final String sql = "SELECT id, name, youtubeLink, tagline FROM lesson WHERE id = ?";


        Lesson lesson= jdbcTemplate.queryForObject(
                sql,
                (resultSet, i) ->{
                    UUID lessonId = UUID.fromString(resultSet.getString("id"));
                    String name= resultSet.getString("name");
                    String youtubeLink= resultSet.getString("youtubeLink");
                    String tagLine =resultSet.getString("tagline");
                    return new Lesson(lessonId, name, youtubeLink, tagLine);
                }, id);
        return Optional.ofNullable(lesson); //might not find student with given id so it can be nullable
    }

    @Override
    public int deleteLessonById(UUID id) {
        final String sql = "DELETE FROM lesson WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }



    @Override
    public int updateLessonById(UUID id, Lesson lesson) {
        final String sql= "UPDATE lesson SET name = ?, youtubeLink = ?, tagline = ? WHERE id = ?";
        return jdbcTemplate.update(sql, lesson.getName(), lesson.getYoutubeLink(), lesson.getTagLine(), id);
    }

}
