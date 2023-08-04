package com.promet.repository.entity;

import com.promet.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class UserProfile extends Base{
    @Id
    private String userId;
    private Long authId;
    private String name;
    private String surname;
    private String identityNumber;
    private String password;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private Long birthDate;
    private String avatar;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
