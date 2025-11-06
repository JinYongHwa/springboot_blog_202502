package kr.ac.mjc.blog02;

import kr.ac.mjc.blog02.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String email){
        return userRepository.findOneByEmail(email);
    }
    
    //회원가입
    public UserResponseDto join(User user){
        UserResponseDto response=new UserResponseDto();
        
        //이메일 체크
        if(user.getEmail().isEmpty()){  //이메일이 비어있을경우
            response.setSuccess(false);
            response.setMessage("이메일을 입력해주세요");
            return response;
        }
        //비밀번호는 4자리 이상인지 체크
        if(user.getPassword().length()<4){
            response.setSuccess(false);
            response.setMessage("비밀번호는 4자리 이상이여야합니다");
            return response;
        }
        User alreadyUser=userRepository.findOneByEmail(user.getEmail());
        if(alreadyUser!=null){      //이미 가입된 이메일
           response.setSuccess(false);
           response.setMessage("이미 가입된 이메일 입니다");
           return response;
        }
        //여기까지 return 이 안됬으면 회원가입 가능한 상태
        User joinedUser=userRepository.save(user);
        response.setSuccess(true);
        response.setUser(joinedUser);
        return  response;
    }

    public UserResponseDto login(User user){
        //아이디와 패스워드 모두 일치하면 일치하는사용자가 리턴, 일치하지않으면  null 리턴
        User loginUser=userRepository.findOneByEmailAndPassword(user.getEmail(),
                user.getPassword());

        UserResponseDto response=new UserResponseDto();
        if(loginUser==null){    //아이디 또는 패스워드가 틀림
            response.setSuccess(false);
            response.setMessage("아이디 또는 패스워드가 틀립니다");
            return response;
        }
        else{   //아이디 패스워드가 모두 일치하는경우 - 로그인성공
            response.setSuccess(true);
            response.setUser(user);
            return response;

        }

    }
}
