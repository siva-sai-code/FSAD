package com.bookexchange.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookexchange.entity.User;
import com.bookexchange.entity.UserRequest;
import com.bookexchange.repository.UserRepository;
import com.bookexchange.service.UserService;

@Service
public class UserSeriviceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public User save(UserRequest userRequest) {
		User user = new User(userRequest.getEmail(), passwordEncoder.encode(userRequest.getPassword()), userRequest.getFullname());
		return userRepository.save(user);
	}

}
