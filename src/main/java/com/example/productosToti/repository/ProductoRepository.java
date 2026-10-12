package com.example.productosToti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productosToti.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}