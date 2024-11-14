package com.bookexchange.service;


import com.bookexchange.entity.User;
import com.bookexchange.entity.UserRequest;

public interface UserService {

	User findByEmail(String username);

	User save(UserRequest userDTO);
}
