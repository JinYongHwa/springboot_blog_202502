package kr.ac.mjc.blog02;

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
    public ResponseEntity<UserResponseDto> login(@RequestBody User user){
        UserResponseDto response= userService.login(user);
        return ResponseEntity.ok(response);
    }
}
