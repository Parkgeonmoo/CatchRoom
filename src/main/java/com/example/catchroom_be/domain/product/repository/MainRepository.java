package com.example.catchroom_be.domain.product.repository;

import com.example.catchroom_be.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<Product, Long>, MainRepositoryCustom{
}
