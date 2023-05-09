package service;

import dto.LoginDTO;
import dto.userDTO;
import response.LoginMessage;

public interface userService {
	String addUser(userDTO userDTO);

	LoginMessage loginUser(LoginDTO loginDTO);

}
