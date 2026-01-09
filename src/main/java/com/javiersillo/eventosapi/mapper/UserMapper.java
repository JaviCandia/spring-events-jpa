package com.javiersillo.eventosapi.mapper;

import com.javiersillo.eventosapi.domain.User;
import com.javiersillo.eventosapi.dto.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true) // Excluimos el password porque nosotros lo encriptamos
    @Mapping(target = "id", ignore = true) // Excluimos el id porque se autogenera
    User toUser(RegisterRequest request);
}
