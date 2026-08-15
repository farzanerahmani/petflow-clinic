package com.roochi.petflowidentity.user.mapper;

import com.roochi.petflowidentity.user.dto.user.AddUserRequestDto;
import com.roochi.petflowidentity.user.dto.user.RoleDto;
import com.roochi.petflowidentity.user.dto.user.UpdateUserRequestDto;
import com.roochi.petflowidentity.user.dto.user.UserDto;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User,UserDto> {

    User toEntity(AddUserRequestDto requestDto);

    User toEntity(UpdateUserRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateUserRequestDto requestDto,
                @MappingTarget User user);

    UserDto toUserDto(User user);

}
