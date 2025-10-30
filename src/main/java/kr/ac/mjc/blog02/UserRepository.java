package kr.ac.mjc.blog02;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    //회원가입시 회원 정보 저장
    User save(User user);

    //이메일 중복 체크
    User findOneByEmail(String email);

    //아이디와 패스워드가 둘다 일치하는 회원 찾기(로그인시 사용)
    User findOneByEmailAndPassword(String email,String password);
}
