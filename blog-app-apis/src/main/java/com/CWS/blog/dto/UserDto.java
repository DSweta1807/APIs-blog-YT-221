package com.CWS.blog.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


	private int id;
	@NotEmpty
	@Size(min = 4, message= "Username must be min of 4 characters")
	private String name;
	@Email(message= "Your email address is not valid")
	
	private String email;
	@NotEmpty
	@Size (min =3, max =10, message="Password must be of min 3 chars and max of 10 chars!!")
	private String password;
	@NotEmpty
	private String about;
	
}
