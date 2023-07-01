package com.example.multipledatasource.repository;

import com.example.multipledatasource.bean.dto.UserEntityDto;
import com.example.multipledatasource.bean.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    private final ModelMapper modelMapper;

    public UserEntityDto insert(final String username, final String email, final String password) {

        UserEntity _new = UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        UserEntity _insert = userEntityRepository.save(_new);

        return modelMapper.map(_insert, UserEntityDto.class);
    }
}
