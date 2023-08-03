package com.promet.repository.entity;


import com.promet.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auth extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    @Email(message = "Lütfen geçerli bir email giriniz.")
    private String email;
    private String password;
    private String name;
    private String surname;
    private String activateCode;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EStatus eStatus = EStatus.PENDING;
    @ElementCollection
    private List<Long> roleIds = new ArrayList<>();
}
