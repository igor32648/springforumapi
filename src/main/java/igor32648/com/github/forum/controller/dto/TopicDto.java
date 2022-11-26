package igor32648.com.github.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import igor32648.com.github.forum.model.Topic;

public class TopicDto {
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	
	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
		}
	
	public static List<TopicDto> convert(List<Topic> topics) {
		return topics.stream().map(TopicDto :: new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}
	
	public LocalDateTime getDataCriacao() {
		return creationDate;
	}
	
	
	
	

}
