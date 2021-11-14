package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class TopicsController {

    @RequestMapping("/topics")
    @ResponseBody
    public List<TopicDto> list() {
        Topic topic = new Topic("Question", "Question about Spring", new Course("Spring", "Development"));

        return TopicDto.convert(Arrays.asList(topic, topic, topic));
    }
}
