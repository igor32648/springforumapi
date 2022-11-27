package igor32648.com.github.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import igor32648.com.github.forum.model.Student;
import igor32648.com.github.forum.repository.StudentRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByEmail(username);
        if (student.isPresent()) {
        	return student.get();
        }
		throw new UsernameNotFoundException("Usuário não encontrado!");
    }

}