package com.promet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetNameAndSurnameFromUserProfileResponseDto {
    private String name;
    private String surname;
}
