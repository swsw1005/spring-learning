package com.example.multipledatasource.bean.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.multipledatasource.bean.entity.UserEntity}
 */
@Data
public class UserEntityDto implements Serializable {
    long id;
    String username;
    String email;
    String password;
}