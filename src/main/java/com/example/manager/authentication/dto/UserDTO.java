package com.example.manager.authentication.dto;


import com.example.manager.authentication.entity.UserRole;
import com.example.manager.enoca.dto.CartDTO;
import com.example.manager.enoca.dto.OrderDTO;
import com.example.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;
    private String email;

    private Set<WorkingUnitDTO> workingUnits;
    private Set<OrderDTO> orders;
    private CartDTO cart;


}
