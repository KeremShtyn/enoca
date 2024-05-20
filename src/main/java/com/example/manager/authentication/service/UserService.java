package com.example.manager.authentication.service;



import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.entity.UserEntity;
import com.example.manager.authentication.repository.UserRepository;
import com.example.manager.enoca.entity.CartEntity;
import com.example.manager.enoca.repository.CartRepository;
import com.example.manager.util.EnocaPageable;
import com.example.manager.authentication.error.ErrorCodes;
import com.example.manager.authentication.mapper.UserMapper;
import com.example.manager.util.exception.EnocaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private UserMapper userMapper;

    private UserRepository userRepository;

    private CartRepository cartRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, CartRepository cartRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomainObject).orElseThrow(() -> new EnocaException(ErrorCodes.THIS_USER_DOES_NOT_EXIST));
    }

    public User findOne(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomainObject).orElseThrow(() -> new EnocaException(ErrorCodes.ACCESS_DENIED));
    }

    public EnocaPageable<User> findAll(int page, int size){
        Page<UserEntity> users = userRepository.findAll(PageRequest.of(page, size));
        return new EnocaPageable<User>(users.getTotalElements(),users.getTotalPages(),users.getPageable(),userMapper.toListDomainObject(users.getContent()));
    }

    public User saveOrUpdate(User user) {
        this.uniquenessUser(user.getUsername());
        UserEntity userEntity = userMapper.toEntity(user);
        if (!CollectionUtils.isEmpty(userEntity.getWorkingUnits())) {
            userEntity.getWorkingUnits().forEach(u -> u.setUser(userEntity));
        }
        createEmptyCartForUser(userMapper.toEntity(user));
        return userMapper.toDomainObject(userRepository.save(userEntity));
    }



    private void uniquenessUser(String username) {
        Optional<UserEntity> usernameOpt = userRepository.findByUsername(username);
        if (usernameOpt.isPresent()) {
            throw new EnocaException(ErrorCodes.THIS_USERNAME_HAS_TAKEN_BEFORE);

        }
    }

    private void createEmptyCartForUser(UserEntity user) {
        CartEntity cart = new CartEntity();
        cart.setTotalPrice(0);
        cart.setQuantity(0);
        cart.setProducts(new HashSet<>());
        cartRepository.save(cart);
        user.setCart(cart);
    }

    public User findById(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EnocaException(ErrorCodes.THIS_USER_DOES_NOT_EXIST));
        User domainObject = userMapper.toDomainObject(user);
        return domainObject;
    }
}
