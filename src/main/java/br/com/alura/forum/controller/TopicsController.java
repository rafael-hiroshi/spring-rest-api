package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.dto.TopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String courseName) {
        List<Topic> topics;
        if (courseName == null) {
            topics = topicRepository.findAll();
        } else {
            topics = topicRepository.findByCourseName(courseName);
        }

        return TopicDto.convert(topics);
    }

    @PostMapping
    public ResponseEntity<TopicDto> store(@RequestBody TopicForm form, UriComponentsBuilder builder) {
        Topic topic = form.toTopic(courseRepository);
        topicRepository.save(topic);

        URI uri = builder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }
}
