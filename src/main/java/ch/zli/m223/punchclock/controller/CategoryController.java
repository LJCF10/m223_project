package ch.zli.m223.punchclock.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private UserDetailsServiceImpl userService;

    public CategoryController(CategoryService categoryservice, UserDetailsServiceImpl userservice){
        this.categoryService = categoryservice;
        this.userService = userservice;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) return;
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@PathVariable long id, @Valid @RequestBody Category category) {

        Optional<Category> editcategory = categoryService.findById(id);
        if (editcategory.isEmpty()) return null;
        category.setId(id);
        return categoryService.updateCategory(category);
    }
    
}
