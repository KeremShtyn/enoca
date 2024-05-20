package com.example.manager.authentication.domain;


import com.example.manager.authentication.entity.UserRole;
import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.domain.Order;
import com.example.manager.util.base.BaseDomain;
import lombok.Data;

import java.util.Set;


@Data
public class User extends BaseDomain {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    private Set<Order> orders;
    private Cart cart;

}
