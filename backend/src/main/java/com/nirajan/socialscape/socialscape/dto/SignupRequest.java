package com.nirajan.socialscape.socialscape.dto;

import com.nirajan.socialscape.socialscape.entity.Role;
import lombok.*;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private Role role;
}
