package com.roochi.petflowidentity.user.mapper;

import com.roochi.petflowidentity.user.dto.user.RoleDto;
import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowshared.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * @author farzane.rahmani
 * @created 7/6/2026
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleDto> {


    RoleDto toRoleDto(Role user);

}
