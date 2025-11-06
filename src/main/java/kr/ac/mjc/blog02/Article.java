package kr.ac.mjc.blog02;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no")
    private int no;     //글번호

    @Column(name="title",nullable = false)
    private String title;   //글제목

    @Column(name="body")
    private String body;    //글 내용

    @CreatedDate
    @Column(name="write_date")
    private LocalDateTime writeDate;    //글작성시간


    //작성자
    @ManyToOne
    private User writeUser;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public String getFormatWriteDate(){
        if(writeDate==null){
            return "";
        }
        return writeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public User getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(User writeUser) {
        this.writeUser = writeUser;
    }
}
