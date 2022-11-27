package igor32648.com.github.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import igor32648.com.github.forum.controller.dto.DetailedTopicDto;
import igor32648.com.github.forum.controller.dto.TopicDto;
import igor32648.com.github.forum.controller.dto.TopicForm;
import igor32648.com.github.forum.controller.dto.UpdateTopicForm;
import igor32648.com.github.forum.model.Topic;
import igor32648.com.github.forum.repository.CourseRepository;
import igor32648.com.github.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	@Cacheable(value = "topicsList")
	public Page<TopicDto> list(@RequestParam(required = false) String courseName,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
		if (courseName == null) {
			Page<Topic> topics = topicRepository.findAll(pagination);
			return TopicDto.convert(topics);
		} else {
			Page<Topic> topicsByCourseName = topicRepository.findByCourseName(courseName, pagination);
			return TopicDto.convert(topicsByCourseName);
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDto> newTopic(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
		Topic newTopic = topicForm.convert(courseRepository);
		topicRepository.save(newTopic);

		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(newTopic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(newTopic));

	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicDto> detailedTopic(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDto> updateTopic(@PathVariable Long id,
			@RequestBody @Valid UpdateTopicForm updateTopicForm) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = updateTopicForm.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}
}
