package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.UserDto;
import com.planyourtrip.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

 //  @Mapping(target = "trip", ignore = true)
    UserDto toDto(User user);

  //  @Mapping(target = "trip", ignore = true)
    User toEntity(UserDto dto);
}
