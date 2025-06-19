package com.example.demo.Course;

import com.example.demo.Topic.Topic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Course {
    @Id
    private String id;
    private String name;
    private String description;
    @ManyToOne
    private Topic topic;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course() {
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public Topic getTopic() {
        return topic;
    }


    public Course(String id, String name, String description, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic=new Topic(topicId," "," ");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
