package com.computershop.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "brands", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private String createBy;

    private String updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

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
