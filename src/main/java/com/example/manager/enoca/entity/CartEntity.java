package com.example.manager.enoca.entity;



import com.example.manager.authentication.entity.UserEntity;
import com.example.manager.util.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SQLDelete(sql = "UPDATE Products SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Carts")
@Table(name = "Carts", indexes = {@Index(columnList = "NAME", name = "product_name_indx")})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
public class CartEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ElementCollection
    @CollectionTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    private Map<ProductEntity, Integer> products = new HashMap<>();

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @Column(name = "QUANTITY")
    private int quantity;
}
