package igor32648.com.github.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor32648.com.github.forum.controller.dto.TopicDto;
import igor32648.com.github.forum.model.Course;
import igor32648.com.github.forum.model.Topic;

@RestController
public class TopicsController {
	@RequestMapping("/topics")
	public List<TopicDto> lista() {
		Topic topic = new Topic("Duvida", "Duvida spring", new Course("Spring Framework"));

		return TopicDto.convert(Arrays.asList(topic, topic, topic));
	}
}
