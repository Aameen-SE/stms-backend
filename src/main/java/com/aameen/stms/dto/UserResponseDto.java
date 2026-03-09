package com.aameen.stms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean active;
}
