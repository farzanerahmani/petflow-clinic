package com.roochi.petflowvisit.dto.cmmon;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
}
