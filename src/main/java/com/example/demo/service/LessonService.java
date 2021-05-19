package com.example.demo.service;

import com.example.demo.dao.LessonDao;
import com.example.demo.model.Lesson;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonService {

    private final LessonDao lessonDao;

    @Autowired //injects into actual constructor
    public LessonService(@Qualifier("po") LessonDao lessonDao) { //bean us connected to fake db
        this.lessonDao = lessonDao;
    }

    public int addLesson(Lesson lesson){
        return lessonDao.insertLesson(lesson);
    }

    public List<Lesson> getAllLessons(){ //returning all the poeple
        return lessonDao.selectAllLessons();
    }

    public Optional<Lesson> getLessonById(UUID id){
        return lessonDao.selectLessonById(id);
    }

    public int deleteLesson(UUID id){
        return lessonDao.deleteLessonById(id);
    }

    public int updateLesson(UUID id,Lesson newLesson){
        return lessonDao.updateLessonById(id, newLesson);
    }
}
