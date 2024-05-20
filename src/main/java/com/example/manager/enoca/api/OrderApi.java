package com.example.manager.enoca.api;
import com.example.manager.enoca.dto.OrderDTO;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.web.bind.annotation.*;

import static com.example.manager.util.api.EnocaAPIEnpoint.CART_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(CART_PATH)
public interface OrderApi {

    @PostMapping
    public EnocaResponse<Object> placeOrder(@PathVariable String username);

    @GetMapping("/{orderCode}")
    public EnocaResponse<Object> getOrderByOrderCode(@PathVariable String orderCode);

    @GetMapping("/{userId}")
    public EnocaResponse<Object> getOrdersByUser(@PathVariable String userId);

}
