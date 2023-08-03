package com.promet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserRequestDto {
    private Long authId;
    private String email;
    private String password;
    private String surname;
    private String name;
}
