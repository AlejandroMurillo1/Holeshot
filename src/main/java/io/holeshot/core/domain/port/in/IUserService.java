package io.holeshot.core.domain.port.in;


import io.holeshot.core.infrastructure.web.dtos.requests.CreateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.UpdateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.UserDetailDTO;

import java.util.List;

public interface IUserService {
    UserDetailDTO createUser(CreateUserDTO dto);
    UserDetailDTO updateUser(UpdateUserDTO dto);
    void deleteUser(Long userId);
    UserDetailDTO findUserById(Long userId);
    List<UserDetailDTO> findUserByName(String name);

}
