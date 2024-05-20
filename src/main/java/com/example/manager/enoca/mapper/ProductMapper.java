package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.entity.ProductEntity;
import com.example.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, Product> {
}
