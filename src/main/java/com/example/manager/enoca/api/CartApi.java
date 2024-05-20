package com.example.manager.enoca.api;

import com.example.manager.enoca.dto.CartDTO;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.web.bind.annotation.*;

import static com.example.manager.util.api.EnocaAPIEnpoint.CART_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(CART_PATH)
public interface CartApi {

    @GetMapping("/{userId}")
    public EnocaResponse<Object> getByUserId(@PathVariable("userId") String userId);

    @PutMapping("/{userId}/products/{productId}")
    public EnocaResponse<Object> updateCart(@PathVariable("userId") String userId, @PathVariable("productId") String productId, @PathVariable("quantity") int quantity);

    @PutMapping("/{userId}/products/{productId}")
    public EnocaResponse<Object> removeFromCart(@PathVariable("userId") String userId, @PathVariable("productId") String productId, @PathVariable("quantity") int quantity);

    @DeleteMapping("/{userId}")
    public void emptyCart(@PathVariable("userId") String userId);

}
