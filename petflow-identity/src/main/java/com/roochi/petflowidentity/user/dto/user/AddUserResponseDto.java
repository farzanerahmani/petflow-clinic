package com.roochi.petflowidentity.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@Data
@Schema(name = "AddUserResponseDto")
public class AddUserResponseDto {
    @Schema(description = "${AddUserResponseDto.id}")
    private Long id;
}
