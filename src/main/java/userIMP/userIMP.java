package userIMP;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import entity.User;
import dto.LoginDTO;
import dto.userDTO;
import repo.userRepo;
import response.LoginMessage;
import service.userService;
public class userIMP implements userService {
	
	@Autowired
    private userRepo userRepo;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
 
    @Override
    public String addUser(userDTO userDTO) {
 
        User user = new User(
 
                userDTO.getUser_id(),
                userDTO.getUser_name(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
 
        userRepo.save(user);
 
        return user.getUser_name();
    }


    userDTO userDTO;
    
    @Override
    public LoginMessage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
 
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }
}
