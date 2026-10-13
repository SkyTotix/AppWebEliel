package com.example.productosToti.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productosToti.model.Category;
import com.example.productosToti.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        category.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(category));
    }
}
