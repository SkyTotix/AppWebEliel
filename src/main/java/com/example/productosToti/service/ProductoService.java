package com.example.productosToti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.productosToti.model.Category;
import com.example.productosToti.model.Producto;
import com.example.productosToti.repository.CategoryRepository;
import com.example.productosToti.repository.ProductoRepository;

@Service
public class ProductoService {

    private ProductoRepository repo;
    private CategoryRepository categoryRepo;

    public ProductoService(ProductoRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    public List<Producto> getAll() {
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    public Producto save(Producto producto, Long categoryId) {
        producto.setCategory(findCategory(categoryId));
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto, Long categoryId) {
        Optional<Producto> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Producto productoDb = optional.get();

        productoDb.setName(producto.getName());
        productoDb.setPrice(producto.getPrice());
        productoDb.setStock(producto.getStock());
        productoDb.setCategory(findCategory(categoryId));

        return Optional.of(repo.save(productoDb));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    private Category findCategory(Long categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Category " + categoryId + " does not exist"));
    }
}
