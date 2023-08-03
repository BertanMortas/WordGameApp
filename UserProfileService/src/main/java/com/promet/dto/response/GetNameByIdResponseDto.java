package com.promet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetNameByIdResponseDto {
    private String name;
    private String surname;
    // yorum
    // yorum 2
}
