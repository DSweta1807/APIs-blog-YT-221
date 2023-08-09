package com.CWS.blog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CWS.blog.dto.UserDto;

import com.CWS.blog.responce.ApiResponse;
import com.CWS.blog.service.userService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private userService userservice;
	
	@PostMapping("/save")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = this.userservice.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	} 
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
		 this.userservice.deleteUser(uid);
//		 return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
//		 ANOTHER WAY ==>> making object nd class for message here
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true),HttpStatus.OK);

	}
	
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAll(){
		return ResponseEntity.ok(this.userservice.getAllUsers());
		
	}
}
