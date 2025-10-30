package kr.ac.mjc.blog02;

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

    @GetMapping("/")
    public ModelAndView main(){
        List<Article> articleList=articleService.getArticleList();
        ModelAndView mav=new ModelAndView();
        mav.addObject("articleList",articleList);
        mav.setViewName("main");
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
    public String articleWrite(@ModelAttribute ArticleDto articleDto){
        Article article=new Article();
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());
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
}
