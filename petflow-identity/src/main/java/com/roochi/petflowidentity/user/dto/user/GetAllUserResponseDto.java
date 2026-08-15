package com.roochi.petflowidentity.user.dto.user;


import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@Schema(name = "GetAllClinicsResponseDto")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetAllUserResponseDto extends PageResponseDto<UserDto> {

}
