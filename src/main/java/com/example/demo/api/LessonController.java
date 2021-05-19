package com.example.demo.api;


import com.example.demo.model.Lesson;
import com.example.demo.service.LessonService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v2/lesson")
@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping //return all users from db
    public List<Lesson> getAllLessons(){
        return lessonService.getAllLessons();
    }

    @GetMapping(path= "{id}") //allows me to have forward slash for the id
    public Lesson getLessonId(@PathVariable("id") UUID id){  //turns id into a UUID pathvariable connects to the path varirable mapping abve
        return lessonService.getLessonById(id)
                .orElse(null); //could have custom message here could add 404
    }

    @PostMapping //makes it an endpoint for post request
    public void addLesson(@Valid @Validated @NotNull @RequestBody Lesson lesson){ //take request body, put inside person
        lessonService.addLesson(lesson); //not null will fail there rather than in our code
    }

    @DeleteMapping(path="{id}") //pass down to service
    public void deleteLessonById(@PathVariable("id")UUID id){
        lessonService.deleteLesson(id);
    }

    @PutMapping(path="{id}")
    public void updateLesson(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Lesson lessonToUpdate){ //takes a person from the request bdy
        lessonService.updateLesson(id, lessonToUpdate);
    }
}
