package kr.ac.mjc.blog02;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView main(HttpSession session){
        List<Article> articleList=articleService.getArticleList();
        ModelAndView mav=new ModelAndView();
        mav.addObject("articleList",articleList);
        mav.setViewName("main");

        User user=(User)session.getAttribute("loginUser");
        if(user!=null){     //로그인이 되어있을경우
            mav.addObject("loginUser",user);
        }

        return mav;
    }


    @GetMapping("/view/article/{no}")
    public ModelAndView articleItem(@PathVariable(name="no") int no){
        ModelAndView mav=new ModelAndView();
        Article article=articleService.getArticle(no);
        mav.addObject("article",article);
        mav.setViewName("item");
        return mav;
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @PostMapping("/article/write")
    public String articleWrite(@ModelAttribute ArticleDto articleDto,HttpSession session){
        Article article=new Article();
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());

        //세션에 저장된 사용자 정보가 작성자가 되도록 설정하기
        User user=(User)session.getAttribute("loginUser");
        User dbUser=userService.getUser(user.getEmail());
        article.setWriteUser(dbUser);

        Article savedArticle=articleService.saveArticle(article);
        return "redirect:/view/article/"+savedArticle.getNo();
    }

    @GetMapping("/article/delete/{no}")
    public String deleteArticle(@PathVariable int no){
        articleService.deleteArticle(no);
        return "redirect:/";
    }
    @GetMapping("/article/modify/{no}")
    public ModelAndView modifyArticle(@PathVariable int no){
        Article article=articleService.getArticle(no);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("modify");
        return mav;
    }

    @PostMapping("/article/modify")
    public String modifyArticleProc(@ModelAttribute ArticleDto articleDto){
        Article article=articleService.updateArticle(articleDto,articleDto.getNo());
        return "redirect:/view/article/"+article.getNo();
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}
