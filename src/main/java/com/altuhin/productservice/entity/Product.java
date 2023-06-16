package com.altuhin.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;


@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)

public class Product extends Auditable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "name")
    private String productName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    
    @Column(name = "units_in_stock")
    private Integer unitsInStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Column(name = "category_id", insertable = false, updatable = false)
    private String categoryId;
    
    @Column(name = "photo")
    private Byte[] photo;
    
    @Column(name = "category_name")
    private String categoryName;
}
