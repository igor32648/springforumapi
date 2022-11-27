package igor32648.com.github.forum.controller.dto;





import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import igor32648.com.github.forum.model.Course;
import igor32648.com.github.forum.model.Topic;
import igor32648.com.github.forum.repository.CourseRepository;

public class TopicForm {
	@NotBlank @NotNull @Length(min = 2)
	private String title;
	@NotBlank @NotNull @Length(min = 2)
	private String message;
	@NotBlank @NotNull @Length(min = 2)
	private String courseName;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Topic convert(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(courseName);
		return new Topic(title, message, course );
	}
	

}
