package igor32648.com.github.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import igor32648.com.github.forum.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findByCourseName(String courseName);

}
