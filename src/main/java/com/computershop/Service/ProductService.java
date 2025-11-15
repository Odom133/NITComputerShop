package com.computershop.Service;

import com.computershop.DTO.Product.ProductRequest;
import com.computershop.DTO.Product.ProductResponse;
import com.computershop.Exception.NotFoundException;
import com.computershop.Mapper.ProductMapper;
import com.computershop.Model.Entity.Brand;
import com.computershop.Model.Entity.Category;
import com.computershop.Model.Entity.Product;
import com.computershop.Repository.BrandRepository;
import com.computershop.Repository.CategoryRepository;
import com.computershop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(ProductRequest payload) {
        Category category = categoryRepository.findById(payload.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")  );
        Brand brand = brandRepository.findById(payload.getBrandId()).orElseThrow(() -> new NotFoundException("Brand not found"));

        Product product = productMapper.toEntity(payload,category,brand);
        productRepository.save(product);

        return productMapper.toResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest payload) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        Category category = categoryRepository.findById(payload.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")  );
        Brand brand = brandRepository.findById(payload.getBrandId()).orElseThrow(() -> new NotFoundException("Brand not found"));

        product.setName(payload.getName());
        product.setDescription(payload.getDescription());
        product.setQuantity(payload.getQuantity());
        product.setPrice(payload.getPrice());
        product.setStatus(payload.getStatus());
        product.setCategory(category);
        product.setBrand(brand);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> list() {
        return productRepository.findAll().stream().map(productMapper::toResponse).toList();
    }

    public List<ProductResponse> filter(Long id, String name, Boolean status, Long categoryId, Long brandId) {
        Specification<Product> spec = Specification.unrestricted();

        if (id != null) {
            spec = spec.and((root, query, cb)
                    -> cb.equal(root.get("id"),id));
        }
        if (name != null) {
            spec = spec.and((root, query, cb)
                    -> cb.like(cb.lower(root.get("name")), "%"+(name).toLowerCase()+"%" ));
        }
        if (status != null) {
            spec = spec.and((root, query, cb)
                    -> cb.equal(root.get("status"),status));
        }
        if (brandId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("brand").get("id"), brandId));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category").get("id"),categoryId));
        }

        Sort sort = Sort.by(Sort.Order.desc("id"));

        return productRepository.findAll(spec,sort).stream().map(productMapper::toResponse).toList();
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return productMapper.toResponse(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
