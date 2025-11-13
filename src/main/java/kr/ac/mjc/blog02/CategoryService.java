package kr.ac.mjc.blog02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    List<Category> getCategoryList(){
       return categoryRepository.findAll();
    }
}
