package com.bookexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookexchange.entity.User;
import com.bookexchange.entity.UserRequest;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User save(UserRequest userDTO);

}
