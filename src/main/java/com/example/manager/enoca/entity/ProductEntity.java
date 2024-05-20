package com.example.manager.enoca.entity;


import com.example.manager.enoca.domain.Product;
import com.example.manager.util.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SQLDelete(sql = "UPDATE Products SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Products")
@Table(name = "Products", indexes = {@Index(columnList = "NAME", name = "product_name_indx")})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
public class ProductEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "STOCK")
    private int stock;

}
