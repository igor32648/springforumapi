package igor32648.com.github.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import igor32648.com.github.forum.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findByCourseName(String courseName, Pageable pagination);

}
