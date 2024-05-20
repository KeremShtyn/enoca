package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.dto.ProductDTO;
import com.example.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper extends BaseDTOMapper<Product, ProductDTO> {
}
