package com.computershop.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false)
    private Double price = 0.0;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private String createBy;

    private LocalDateTime updateAt;
    private String updateBy;

    // relationship

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    private Brand brand;

    @PrePersist
    public void onCreate() {
        this.createAt = LocalDateTime.now();
        if (this.status == null) this.status = true;
    }
    @PreUpdate
    public void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
