package com.example.demo.dao;

import com.example.demo.model.Lesson;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonDao {

    int insertLesson(UUID id, Lesson lesson); //insert person with an id

    default int insertLesson(Lesson lesson){
        UUID id = UUID.randomUUID(); //allows to create UUID id ourselves, randomly generated
        return insertLesson(id, lesson);
    }

    List<Lesson> selectAllLessons(); //contract for returning all lesson

    Optional<Lesson> selectLessonById(UUID id); //get by id

    int deleteLessonById(UUID id);

    int updateLessonById(UUID id,Lesson lesson);
}
