package chienlvm.fsoft.vn.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import chienlvm.fpt.vn.commom.TokenUtils;
import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.repositioty.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {
	@Value("${token.expiration_time}")
	private static long EXPIRATIONTIME; // 10 days

	@Value("${token.secret_key}")
	private static String SECRET;

	@Value("${token.token_prefix}")
	private static String TOKEN_PREFIX;

	@Value("${token.header_string}")
	private static String HEADER_STRING;

	@Autowired
	private static UserRepository userRepository;

	@Autowired
	public TokenAuthenticationService(@Value("${token.expiration_time}") String expirationTime,
			@Value("${token.secret_key}") String secretKey, @Value("${token.token_prefix}") String tokenPrefix,
			@Value("${token.header_string}") String header_string, UserRepository userRepository) {
		TokenAuthenticationService.EXPIRATIONTIME = Long.parseLong(expirationTime);
		TokenAuthenticationService.SECRET = secretKey;
		TokenAuthenticationService.TOKEN_PREFIX = tokenPrefix;
		TokenAuthenticationService.HEADER_STRING = header_string;
		TokenAuthenticationService.userRepository = userRepository;
	}

	public static String token(String userId, String userName) {
		String key = userId + "_" + userName;
		return Jwts.builder().setSubject(key).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	/**
	 * Step 1 get auth_key in db Step 2 decode auth_key with current token in
	 * request If matching data return OK Else return fail
	 * 
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String chainToken = null;
			String tokeRequest = token.substring(token.indexOf(".") + 1, token.length());
			try {
				chainToken = Jwts.parser().setSigningKey(SECRET)
						.parseClaimsJws(token.replace(token.substring(0, token.indexOf(".") + 1), "")).getBody().getSubject();
				DecodedJWT jwt = JWT.decode(token.substring(token.indexOf(".") + 1, token.length()));
				if (jwt.getExpiresAt().before(new Date())) {
					return null;
				}
				if (!chainToken.isEmpty()) {
					UserEntity userDetail = userRepository.findByUserName(chainToken.split("_")[1]);
					if (tokeRequest.equals(userDetail.getAuthKey())) {
						userDetail.setRole(1);
						return new UsernamePasswordAuthenticationToken(userDetail, null, Collections.emptyList());
					} else {
						return null;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return null;
	}

	/**
	 * Add token to response header
	 * 
	 * @param response
	 * @param useInfo
	 * @return return token for step 2
	 */
	public static String AddTokenToHeader(HttpServletResponse response, UserEntity useInfo) {
		String JWT = null;
		try {
			JWT = TokenUtils.generateToken(useInfo.getUserId(), useInfo.getUserName(), SECRET, EXPIRATIONTIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String token = TokenUtils.prefixEncode(TOKEN_PREFIX) + "." + JWT;
		Cookie cookie = new Cookie(HEADER_STRING, token);
		cookie.setPath("/");
		response.addCookie(cookie);
		return JWT;
	}

}