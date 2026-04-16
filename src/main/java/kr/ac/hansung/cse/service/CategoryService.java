package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.exception.DuplicateCategoryException;
import kr.ac.hansung.cse.model.Category;
import kr.ac.hansung.cse.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 전체 카테고리 목록
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 새 카테고리 등록.
    @Transactional
    public Category createCategory(String name) {
        categoryRepository.findByName(name).ifPresent(c -> {
            throw new DuplicateCategoryException(name);
        });

        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    // 카테고리 삭제
    @Transactional
    public void deleteCategory(Long id) {
        long count = categoryRepository.countProductsByCategoryId(id);
        if (count > 0) {
            throw new IllegalStateException("해당 카테고리에 " + count + "개의 상품이 등록되어 있어 삭제할 수 없습니다.");
        }
        categoryRepository.delete(id);
    }
}
