package chienlvm.fsoft.vn.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fpt.vn.commom.TokenUtils;
import chienlvm.fsoft.vn.dto.request.JwtRequest;
import chienlvm.fsoft.vn.dto.request.UserRequest;
import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.repositories.UserRepository;
import chienlvm.fsoft.vn.service.TokenAuthenticationService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/")
public class JwtAuthenticationController {
	@Autowired(required = false)
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepositiory;

	@RequestMapping(value = "/authenticationToken", method = RequestMethod.POST)
	public ResponseData authenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		ResponseData responseData = ResponseData.create();
		String tokenRequest = authenticationRequest.getToken();
		if ("".equals(tokenRequest) || tokenRequest == null) {
			responseData.setData("error", "Lỗi xác thực");
			return responseData;
		}
		// token prefix
		// token decode
		String tokeDecode = TokenUtils.decodeToken(tokenRequest);
		// get user from token
		UserEntity userDetail = userRepositiory.findByUserName(tokeDecode.split("_")[1]);
		tokenRequest = tokenRequest.substring(tokenRequest.indexOf(".") + 1, tokenRequest.length());
		if (tokenRequest.equals(userDetail.getAuthKey())) {
			new UsernamePasswordAuthenticationToken(userDetail, null, Collections.emptyList());
			responseData.setData("isLogin", true);
			responseData.setData("accountSetting", userDetail);
		} else {
			new UsernamePasswordAuthenticationToken(null, null);
			responseData.setData("error", "Lỗi xác thực");
		}
		return responseData;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseData createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
			HttpServletResponse response) throws Exception {
		ResponseData responseData = ResponseData.create();

		String userName = authenticationRequest.getUsername();
		if (authenticationRequest.getUsername().isEmpty()) {
			responseData.setData("error", "User không được trống");
			return responseData;
		} else if (authenticationRequest.getPassword().isEmpty()) {
			responseData.setData("error", "Password không được trống");
			return responseData;
		}
		UserEntity useInfo = userRepositiory.findByUserName(userName);
		int loginFail = 0;
		if (useInfo != null) {
			useInfo.getLoginFailNum();
			if (loginFail > 10) {
				useInfo.setEnabled(1);
				userRepositiory.save(useInfo);
				responseData.setData("error", "Tài khoản đã bị khóa");
				return responseData;
			}
		}

		Authentication authenticate = null;
		// check login
		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userName, authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			if (useInfo != null) {
				useInfo.setLoginFailNum(loginFail + 1);
				userRepositiory.save(useInfo);
			}

			throw new Exception("INVALID_CREDENTIALS", e);
		}
		// set response headr
		String token = TokenAuthenticationService.AddTokenToHeader(response, useInfo);

		if (authenticate.isAuthenticated()) {
			// if login OK then set info user
			new UsernamePasswordAuthenticationToken(useInfo, null, Collections.emptyList());
			useInfo.setAuthKey(token);
			userRepositiory.save(useInfo);
			responseData.setData("isLogin", true);
			responseData.setData("accountSetting", useInfo);
		} else {
			useInfo.setLoginFailNum(loginFail + 1);
			userRepositiory.save(useInfo);
			responseData.setData("error", "Tài khoản hoặc mật khẩu không đúng");
		}
		return responseData;
	}

	/**
	 * register new user
	 * 
	 * @param authenticationRequest
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseData createUser(@RequestBody UserRequest userRequest) throws Exception {
		ResponseData responseData = ResponseData.create();
		if (userRequest.getUsername().isEmpty()) {
			responseData.setData("error", "User không được trống");
			return responseData;
		} else if (userRequest.getPassword().isEmpty()) {
			responseData.setData("error", "Password không được trống");
			return responseData;
		} else if (userRequest.getPassword().isEmpty()) {
			responseData.setData("error", "Password không được trống");
			return responseData;
		}
		UserEntity useInfo = userRepositiory.findByUserName(userRequest.getUsername());
		if (useInfo != null) {
			responseData.setData("error", "User đã tồn tại");
		} else {
			UserEntity userData = new UserEntity();
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String pw = bCryptPasswordEncoder.encode(userRequest.getPassword());
			userData.setHashPassword(pw);
			// admin : 1, use : 0
			userData.setRole(0);
			userData.setUserName(userRequest.getUsername());
			userData.setEmail(userRequest.getEmail());
			UserEntity result = userRepositiory.save(userData);
			responseData.setData("data", result);
		}
		return responseData;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseData logout() throws Exception {
		ResponseData responseData = ResponseData.create();
		SecurityContextHolder.getContext().setAuthentication(null);
		return responseData;
	}
}
