package com.computershop.Repository;

import com.computershop.Model.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByCode(String code);
}
