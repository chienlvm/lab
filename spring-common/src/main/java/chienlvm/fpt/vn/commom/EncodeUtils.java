package chienlvm.fpt.vn.commom;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncodeUtils {
	/**
	 * encode base64
	 * @param stringEncode
	 * @param randomText
	 * @return
	 */
	public static String encodeBase64(String stringEncode, String randomText) {
		String str = stringEncode + randomText;
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		return new String(bytesEncoded);
	}
	/**
	 * Decode base64
	 * @param stringDecode
	 * @return
	 */
	public static String decodeBase64(String stringDecode) {
		byte[] valueDecoded = Base64.decodeBase64(stringDecode);
		return new String(valueDecoded);
	}
}
