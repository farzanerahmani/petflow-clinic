package com.roochi.petflowidentity.user.dto.user;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserByIdRequestDto
{
    private Long userId;
}
