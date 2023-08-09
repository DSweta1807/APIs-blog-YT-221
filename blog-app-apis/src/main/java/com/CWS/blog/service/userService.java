package com.CWS.blog.service;

import java.util.List;

import com.CWS.blog.dto.UserDto;



public interface userService {

	UserDto createUser(UserDto user);
	UserDto updateUser (UserDto user, Integer userId);
	UserDto getUserById (Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser (Integer userId);
	
	
}
