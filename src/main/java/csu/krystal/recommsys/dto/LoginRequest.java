package csu.krystal.recommsys.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private int role;
    private String username;
    private String password;
}