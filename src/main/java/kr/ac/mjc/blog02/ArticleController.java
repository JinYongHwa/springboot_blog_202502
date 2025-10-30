package kr.ac.mjc.blog02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody Article article){
        //데이터베이스에 값을 저장한다
        Article savedArticle=articleService.saveArticle(article);
        
        //응답으로 보낼 객체를 설정
        ArticleResponse articleResponse=new ArticleResponse();
        articleResponse.setSuccess(true);
        articleResponse.setMessage("글작성이 완료되었습니다");
        articleResponse.setArticle(savedArticle);
        
        //응답을 spring boot 에 보냄
        return ResponseEntity.ok(articleResponse);
    }

    @DeleteMapping("/article/{no}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable(name="no") int no){
        boolean success=articleService.deleteArticle(no);
        ArticleResponse articleResponse=new ArticleResponse();
        articleResponse.setSuccess(success);

        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping("/article/{no}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable(name="no") int no){
        Article article=articleService.getArticle(no);

        ArticleResponse articleResponse=new ArticleResponse();
        articleResponse.setSuccess(true);
        articleResponse.setArticle(article);
        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping("/articles")
    public ResponseEntity<ArticleResponse> getArticleList(){
        List<Article> articleList=articleService.getArticleList();
        ArticleResponse articleResponse=new ArticleResponse();
        articleResponse.setSuccess(true);
        articleResponse.setArticleList(articleList);
        return ResponseEntity.ok(articleResponse);
    }


    @PutMapping("/article/{no}")
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleDto articleDto, @PathVariable(name="no")int no){
        Article updatedArticle=articleService.updateArticle(articleDto,no);

        ArticleResponse articleResponse=new ArticleResponse();
        articleResponse.setSuccess(true);
        articleResponse.setArticle(updatedArticle);

        return ResponseEntity.ok(articleResponse);

    }

}
