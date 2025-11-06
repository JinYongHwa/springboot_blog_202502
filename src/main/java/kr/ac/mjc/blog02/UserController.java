package kr.ac.mjc.blog02;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog02.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody User user){
        UserResponseDto response= userService.join(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody User user, HttpSession session){
        UserResponseDto response= userService.login(user);
        if(response.isSuccess()){       //로그인 성공시
            session.setAttribute("loginUser",response.getUser());
        }
        return ResponseEntity.ok(response);
    }
}
