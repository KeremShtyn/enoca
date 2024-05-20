package com.example.manager.authentication.controller;


import com.example.manager.authentication.api.AuthAPI;
import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.dto.TokenRequestDTO;
import com.example.manager.authentication.dto.TokenResponseDTO;
import com.example.manager.authentication.dto.UserDTO;
import com.example.manager.authentication.dto.UserDataDTO;
import com.example.manager.authentication.mapper.UserDTOMapper;
import com.example.manager.authentication.mapper.UserDataDTOMapper;
import com.example.manager.security.JwtTokenUtil;
import com.example.manager.authentication.service.UserService;
import com.example.manager.util.EnocaPageable;
import com.example.manager.util.response.EnocaGenerator;
import com.example.manager.util.response.EnocaResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class AuthController implements AuthAPI {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDTOMapper userDTOMapper;

    private final UserDataDTOMapper userDataDTOMapper;


    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil, UserDTOMapper userDTOMapper, UserDataDTOMapper userDataDTOMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDTOMapper = userDTOMapper;
        this.userDataDTOMapper = userDataDTOMapper;
    }

    @Override
    public EnocaResponse<Object> token(TokenRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userService.findOne(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        final UserDataDTO userData = userDataDTOMapper.toDTO(user);
        TokenResponseDTO response = new TokenResponseDTO();
        response.setAccessToken(token);
        response.setUserData(userData);


        return EnocaGenerator.generateSignResponse(response);
    }

    @Override
    public EnocaResponse<Object> saveUser(UserDTO userDTO) {

        User domainObject = userDTOMapper.toDomain(userDTO);
        domainObject.setPassword(new BCryptPasswordEncoder().encode(domainObject.getPassword()));

        return EnocaGenerator.generateSignResponse(userDTOMapper.toDTO(userService.findOne(domainObject.getUsername())));
    }

    @Override
    public EnocaResponse<Object> update(UserDTO userDTO) {
        return saveUser(userDTO);
    }

    @Override
    public EnocaResponse<Object> getByUsername(String username) {
        User user = userService.findByUsername(username);
        return EnocaGenerator.generateSignResponse(userDTOMapper.toDTO(user));
    }

    @Override
    public EnocaResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        EnocaPageable<User> userPage = userService.findAll(page, size);
        return EnocaGenerator.generateSignResponse(userPage);
    }

}
