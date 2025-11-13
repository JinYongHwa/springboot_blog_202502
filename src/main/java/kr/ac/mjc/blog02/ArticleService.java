package kr.ac.mjc.blog02;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Article saveArticle(Article article){

        List<Category> categoryList=new ArrayList<>();
        for(int categoryId : article.getCategoryIds()){
            Category category=categoryRepository.findById(categoryId).get();
            categoryList.add(category);
        }
        article.setCategoryList(categoryList);

        Article savedArticle=articleRepository.save(article);
        return savedArticle;
    }

    public boolean deleteArticle(int no){
        boolean success=true;
        articleRepository.deleteById(no);
        return success;
    }

    public Article getArticle(int no){
        Article article=articleRepository.findById(no);
        return article;
    }

    public List<Article> getArticleList(){
        List<Article> articleList=articleRepository.findAllByOrderByWriteDateDesc();
        return articleList;
    }

    @Transactional
    public Article updateArticle(ArticleDto articleDto,int no){
        Article article=articleRepository.findById(no);
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());

        return article;
    }

}
