package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDetailsDto;
import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.dto.TopicForm;
import br.com.alura.forum.dto.UpdateTopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    @Transactional
    public ResponseEntity<TopicDto> store(@RequestBody @Valid TopicForm form, UriComponentsBuilder builder) {
        Topic topic = form.toTopic(courseRepository);
        topicRepository.save(topic);

        URI uri = builder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public TopicDetailsDto show(@PathVariable Long id) {
        Topic topic = topicRepository.getById(id);
        return new TopicDetailsDto(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
        Topic topic = form.update(id, topicRepository);

        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
