package com.promet.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordMailModel implements Serializable {
    private Long authId;
    private String email;
    private String password;
}
