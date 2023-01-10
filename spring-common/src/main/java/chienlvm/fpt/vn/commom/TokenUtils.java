package chienlvm.fpt.vn.commom;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {
	static final String SECRET = "f87124ca-8460-11ed-a1eb-0242ac120002";
	static final String TOKEN_PREFIX = "r7-tect";
	static final String HEADER_STRING = "token";

	@Value("${rootPath}")
	private static String rootPath;

	@Autowired
	public TokenUtils(@Value("${rootPath}") String rootPath) {
		TokenUtils.rootPath = rootPath;
	}

	/**
	 * Token decode
	 * 
	 * @param token
	 * @param secretKey
	 * @param prefix
	 * @param headerString
	 * @return
	 */
	public static String decodeToken(String token, String secretKey, String prefix, String headerString) {

		return secretKey;

	}

	/**
	 * Verify token when user request
	 * 
	 * @param token
	 * @param secretKey
	 * @param prefix
	 * @param headerString
	 * @return
	 */
	public static boolean verifyToken() {
		return false;

	}

	/**
	 * GenerateToken when use login success subjectKey = subjectKey = userId + "_" +
	 * userName + "_" + String.valueOf(new Date().getTime());
	 * 
	 * @param userId
	 * @param userName
	 * @param secretKey
	 * @param expirationTime
	 * @return token
	 */
	public static String generateToken(Long userId, String userName, String secretKey, Long expirationTime) {
		String subjectKey = userId + "_" + userName + "_" + String.valueOf(new Date().getTime());
		return Jwts.builder().setSubject(subjectKey)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();

	}
	public static String getPrefixFromToken(String token) {
		return token.substring(0, token.indexOf('.'));
	}
	public static String prefixEncode(String prefix) {
		return EncodeUtils.encodeBase64(prefix, String.valueOf(new Date().getTime()));
	}
	
	public static String prefixDecode(String token) {
		return EncodeUtils.decodeBase64(token);
	}
	public static String decodeToken(String token) {
		String result = null;
		try {
			result = Jwts.parser().setSigningKey(SECRET)
			.parseClaimsJws(token.replace(token.substring(0, token.indexOf(".") + 1), "")).getBody().getSubject();
		} catch (Exception e) {
			return result;
			// TODO: handle exception
		}
		return result;
	}
}
