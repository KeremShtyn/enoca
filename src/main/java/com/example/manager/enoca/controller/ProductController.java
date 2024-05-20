package com.example.manager.enoca.controller;

import com.example.manager.enoca.api.ProductApi;
import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.dto.ProductDTO;
import com.example.manager.enoca.mapper.ProductDTOMapper;
import com.example.manager.enoca.service.ProductService;
import com.example.manager.util.response.EnocaGenerator;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController implements ProductApi {


    private ProductService productService;

    private ProductDTOMapper productDTOMapper;

    public ProductController(ProductService productService, ProductDTOMapper productDTOMapper) {
        this.productService = productService;
        this.productDTOMapper = productDTOMapper;
    }



    @Override
    public EnocaResponse<Object> getById(String id) {
        Product product = productService.findById(id);
        return EnocaGenerator.generateSignResponse(productDTOMapper.toDTO(product));
    }

    @Override
    public EnocaResponse<Object> save(ProductDTO productDTO) {
        Product product = productDTOMapper.toDomain(productDTO);
        product = productService.createProduct(product);
        return EnocaGenerator.generateSignResponse(productDTOMapper.toDTO(product));
    }

    @Override
    public EnocaResponse<Object> update(ProductDTO productDTO) {
        return save(productDTO);
    }

    @Override
    public void delete(String id) {
        productService.deleteProduct(id);
    }
}
