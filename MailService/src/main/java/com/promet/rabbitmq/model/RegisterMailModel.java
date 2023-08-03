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
public class RegisterMailModel implements Serializable {
    private Long authId;
    private String email;
    private String activationCode;
    private String name;
    private String surname;

}
