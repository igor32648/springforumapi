package igor32648.com.github.forum.controller.dto;

import java.time.LocalDateTime;

import igor32648.com.github.forum.model.Answer;

public class AnswerDto {
	
	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	
	
	
	public AnswerDto(Answer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.creationDate = answer.getCreationDate();
		this.authorName = answer.getAuthor().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	

}
