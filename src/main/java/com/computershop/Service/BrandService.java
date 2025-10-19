package com.computershop.Service;

import com.computershop.DTO.Brand.BrandRequest;
import com.computershop.DTO.Brand.BrandResponse;
import com.computershop.Exception.NotFoundException;
import com.computershop.Mapper.BrandMapper;
import com.computershop.Model.Entity.Brand;
import com.computershop.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandResponse> getAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BrandResponse getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new NotFoundException("Brand Not Found!"));
        return brandMapper.toResponse(brand);
    }

    public BrandResponse create(BrandRequest request) {
        if(brandRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Brand code already exists");
        }
        Brand brand = brandMapper.toEntity(request);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponse(saved);
    }

    public BrandResponse update(Long id, BrandRequest request) {
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new NotFoundException("Brand not found with id " + id));

        brand.setName(request.getName());
        brand.setCode(request.getCode());
        brand.setDescription(request.getDescription());
        brand.setStatus(request.getStatus());
        brand.setUpdateBy(request.getUpdateBy());

        Brand updated = brandRepository.save(brand);
        return brandMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand not found with id " + id));
            brandRepository.delete(brand);
    }

}
