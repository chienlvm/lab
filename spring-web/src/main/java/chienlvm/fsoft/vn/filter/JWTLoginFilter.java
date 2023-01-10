package chienlvm.fsoft.vn.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import chienlvm.fsoft.vn.service.UserDetailsServiceImpl;
import chienlvm.fsoft.vn.utils.ResultDto;
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static UserDetailsServiceImpl userDetailsService;
	@Autowired
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		userDetailsService = new UserDetailsServiceImpl();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// get db user/password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
	}

	/**
	 * If param request attachment param "remember" then set author_key Otherwise
	 * not set
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String uuid = UUID.randomUUID().toString();
		String keyToken = authResult.getName() + uuid;
		userDetailsService.loadUserByUsername("chienlvm"+ keyToken);
		// TokenAuthenticationService.addAuthentication(response, keyToken);
	}

	/**
	 * Override method login error
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		String message = "User and password incorrect!";
		String status = "FAILED";
		ResultDto resul = new ResultDto(status, message);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(mapper.writeValueAsString(resul));
	}

}