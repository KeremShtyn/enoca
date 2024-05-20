package com.example.manager.enoca.api;

import com.example.manager.enoca.dto.ProductDTO;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.manager.util.api.EnocaAPIEnpoint.PRODUCT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(PRODUCT_PATH)
public interface ProductApi {

    @GetMapping("/{id}")
    public EnocaResponse<Object> getById(@PathVariable("id") String id);

    @PostMapping
    public EnocaResponse<Object> save(@RequestBody ProductDTO productDTO);

    @PutMapping
    public EnocaResponse<Object> update(@RequestBody ProductDTO productDTO);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id);
}
