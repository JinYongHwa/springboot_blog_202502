package kr.ac.mjc.blog02;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

    Article save(Article article);

    void deleteById(int no);

    Article findById(int no);

    List<Article> findAll();

    List<Article> findAllByOrderByWriteDateDesc();

}
