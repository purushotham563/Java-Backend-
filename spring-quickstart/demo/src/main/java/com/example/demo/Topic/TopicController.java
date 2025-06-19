package com.example.demo.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;
    @RequestMapping("/topics")
    public List<Topic> getAllTopic(){
      return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id){
      return topicService.getTopic(id);
    }
    @PostMapping("/topics")
    public void addTopic(@RequestBody Topic topic){
       topicService.addTopic(topic);
    }
    @PutMapping("/topics/{id}")
    public void updateTopics(@RequestBody Topic topic,@PathVariable String id){
      topicService.updateTopic(id,topic);
    }
    @DeleteMapping("/topics/{id}")
    public void deleteTopic(@PathVariable String id){
        topicService.delete(id);
    }



}
