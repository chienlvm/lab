package chienlvm.fsoft.vn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.repositioty.UserRepository;
import chienlvm.fsoft.vn.utils.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity appUser = userRepository.findByUserName(userName);
		if (appUser == null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (appUser.getRole() == 0 || appUser.getRole() == 1) {
			for (Role role : Role.values()) {
				if (role.getRoleValue() == appUser.getRole()) {
					GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
					grantList.add(authority);
				}
			}
		}
		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
				appUser.getHashPassword(), grantList);
		return userDetails;
	}
}