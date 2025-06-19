package com.example.demo.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    public List<Topic>getAllTopics(){
   List<Topic>topics=new ArrayList<>();

    topicRepository.findAll().forEach(topics::add);
    return topics;
}
public Topic getTopic(String id){
  return topicRepository.findById(id).orElse(null);

}
    public void addTopic(Topic topic) {
    topicRepository.save(topic);
    }
    public void updateTopic(String id, Topic topic) {
     topicRepository.save(topic);
    }

    public void delete(String id) {
      topicRepository.deleteById(id);
    }
}

