package com.example.manager.authentication.api;


import com.example.manager.authentication.dto.TokenRequestDTO;
import com.example.manager.authentication.dto.UserDTO;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.manager.util.api.EnocaAPIEnpoint.*;

@CrossOrigin(origins = "*")
@RequestMapping(AUTH_PATH)
public interface AuthAPI {

    @PostMapping(CREATE_TOKEN)
    public EnocaResponse<Object> token(@RequestBody TokenRequestDTO request);

    @PostMapping(CREATE_USER)
    public EnocaResponse<Object> saveUser(@RequestBody UserDTO userDTO);

    @PutMapping(CREATE_USER)
    public EnocaResponse<Object> update(@RequestBody UserDTO userDTO);

    @GetMapping("/getByUsername/{username}")
    public EnocaResponse<Object> getByUsername(@PathVariable("username") String username);

    @GetMapping("/users")
    public EnocaResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);



}
