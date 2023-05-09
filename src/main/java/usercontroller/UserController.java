package usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.LoginMessage;
import dto.LoginDTO;
import dto.userDTO;
import service.userService;

@RestController
@CrossOrigin
@RequestMapping("api/vi/user")
public class UserController {
	@Autowired
	private userService userService;
	@PostMapping(path="/save")
	public String saveUser(@RequestBody userDTO userDTO ) {
		String id = userService.addUser(userDTO);
		return id;
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
		LoginMessage loginMessage = userService.loginUser(loginDTO);
		return ResponseEntity.ok(loginMessage);
		
	}
}
