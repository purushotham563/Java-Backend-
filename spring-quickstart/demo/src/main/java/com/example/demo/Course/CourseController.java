package com.example.demo.Course;

import com.example.demo.Topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/topics/{id}/courses")
    public List<Course> getAllCourse(@PathVariable String id ){
      return courseService.getAllCourses(id);
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String id){

        return courseService.getCourse(id);
    }
    @PostMapping("/topics/{topicId}/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course course, @PathVariable String topicId) {
        try {
            course.setTopic(new Topic(topicId, "", ""));
            courseService.addCourse(course);
            return ResponseEntity.ok("Courses added");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error"+e.getMessage());
        }

    }

    @PutMapping("/topics/{topicId}/courses/{Id}")
    public void updateCourse(@RequestBody Course course,@PathVariable String id, @PathVariable String topicId){
        course.setTopic(new Topic(topicId," "," "));
        courseService.updateCourse( course);
    }
    @DeleteMapping("/topics/{topicId}/courses/{Id}")
    public void deleteCourse(@PathVariable String Id){
        courseService.deleteCourse(Id);
    }



}
