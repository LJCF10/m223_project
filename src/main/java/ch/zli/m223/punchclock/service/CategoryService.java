package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Entry createCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public List<Entry> findAll() {
        return categoryRepository.findAll();
    }

    public Entry findById(long id){
        return categoryRepository.findById(id);
    }

    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

    public Entry updateCategory(Category category){
        return categoryRepository.save(category);
    }
}
