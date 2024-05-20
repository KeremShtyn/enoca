package com.example.manager.authentication.dto;


import com.example.manager.authentication.entity.UserRole;
import com.example.manager.util.base.BaseDTO;
import lombok.Data;

@Data
public class WorkingUnitDTO extends BaseDTO {

    private String workingType;
    private String workingId;
    private UserRole role;
    private String userId;
}
