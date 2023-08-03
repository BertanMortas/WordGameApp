package com.promet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRequestDto {
    private String token;
    @NotEmpty
    @NotBlank
    private String keyWord;
    @NotEmpty
    @NotBlank
    private String gameName;
}
