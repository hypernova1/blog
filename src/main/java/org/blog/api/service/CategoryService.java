package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Category;
import org.blog.api.exception.CategoryNotFoundException;
import org.blog.api.repository.CategoryRepository;
import org.blog.api.web.payload.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melchor
 * Date: 2020/09/21
 * Time: 9:02 PM
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categories;
    private final ModelMapper modelMapper;

    public List<CategoryDto> getList() {
        return categories.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    public Long register(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return categories.save(category).getId();
    }

    @Transactional
    public void update(Long id, CategoryDto categoryDto) {
        Category category = categories.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(categoryDto.getName()));
        category.update(categoryDto);
    }
}
