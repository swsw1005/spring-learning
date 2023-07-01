package com.example.transactiontest.bean.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class UserEntityDto implements Serializable {
    long id;
    String username;
    String email;
    String password;
}