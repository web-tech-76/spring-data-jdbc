package jpa.app.entities.retail;

import jakarta.persistence.*;
import jpa.app.entities.shared.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "expiry",nullable = false)
    private Date expiryDate;

    @Column(name = "is_international",nullable = false)
    private Boolean isInternational;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

}
