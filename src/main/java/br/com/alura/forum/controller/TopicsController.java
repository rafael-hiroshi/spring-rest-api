package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDetailsDto;
import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.dto.TopicForm;
import br.com.alura.forum.dto.UpdateTopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public Page<TopicDto> list(@RequestParam(required = false) String courseName, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (courseName == null) {
            return TopicDto.convert(topicRepository.findAll(pageable));
        }

        return TopicDto.convert(topicRepository.findByCourseName(courseName, pageable));
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
    public ResponseEntity<TopicDetailsDto> show(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new TopicDetailsDto(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDetailsDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            Topic topic = form.update(id, topicRepository);

            return ResponseEntity.ok(new TopicDetailsDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            topicRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
