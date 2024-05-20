package com.example.manager.enoca.service;

import com.example.manager.authentication.error.ErrorCodes;
import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.entity.ProductEntity;
import com.example.manager.enoca.mapper.ProductMapper;
import com.example.manager.enoca.repository.ProductRepository;
import com.example.manager.util.exception.EnocaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product createProduct(Product product){
        this.validatePayment(product);
        return save(product);
    }

    public Product findById(String id){
        return productRepository.findById(id).map(productMapper::toDomainObject).orElseThrow(() -> new EnocaException(ErrorCodes.DATA_NOT_FOUND));
    }

    public void deleteProduct(String id){
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
        else {
            throw new EnocaException(ErrorCodes.DATA_NOT_FOUND);
        }
    }

    private Product save(Product product){
        ProductEntity save = productRepository.save(productMapper.toEntity(product));
        return productMapper.toDomainObject(save);
    }

    private void validatePayment(Product product){
        if (Objects.isNull(product.getName())){
            throw new EnocaException(ErrorCodes.PRODUCT_NAME_CAN_NOT_BE_EMPTY);
        }
        if (Objects.isNull(product.getPrice())){
            throw new EnocaException(ErrorCodes.PRODUCT_PRICE_CAN_NOT_BE_EMPTY);
        }

    }

}
