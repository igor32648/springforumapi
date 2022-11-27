package igor32648.com.github.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import igor32648.com.github.forum.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);

}
