package igor32648.com.github.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import igor32648.com.github.forum.model.Student;
import igor32648.com.github.forum.repository.StudentRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private StudentRepository studentRepository;
	
	public TokenAuthenticationFilter(TokenService tokenService, StudentRepository studentRepository) {
		super();
		this.tokenService = tokenService;
		this.studentRepository = studentRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoverToken(request);
        boolean valid = tokenService.tokenIsValid(token);
		if (valid) {
			clientAuthenticate(token);
		}
		filterChain.doFilter(request, response);
	}

	private void clientAuthenticate(String token) {
		Long userId = tokenService.getUserId(token);
		Student user = studentRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
	    if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
	        return null;
	    }

	    return token.substring(7, token.length());
	}

}
