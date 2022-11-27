package igor32648.com.github.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import igor32648.com.github.forum.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


	Course findByName(String name);

	

}
