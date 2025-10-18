package com.computershop.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, unique = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Boolean status;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private String createBy;

    private LocalDateTime updateAt;
    private String updateBy;

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
