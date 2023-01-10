package chienlvm.fsoft.vn.config;
/**
 * Class handle error authorized
 */
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		String message = "Unauthorized";
//		String status = "FAILED";
//		ResultDto resul = new ResultDto(status, message);
//		response.setStatus(HttpStatus.OK.value());
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(mapper.writeValueAsString(resul));
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
	}
}