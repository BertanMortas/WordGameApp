package com.promet.dto.request;

import com.promet.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FromAuthToUserProfileActivateManagerStatusRequestDto {
    private Long authId;
    private EStatus status;
}
