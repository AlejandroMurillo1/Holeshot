package io.holeshot.core.application.service;

import io.holeshot.core.domain.model.user.User;
import io.holeshot.core.domain.port.in.IUserService;
import io.holeshot.core.infrastructure.persistence.UserRepository;
import io.holeshot.core.infrastructure.web.dtos.requests.CreateAthleteDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.CreateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.UpdateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.AthleteDetailDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.UserDetailDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailDTO createUser(CreateUserDTO dto) {
        if(userRepository.findByDocumentNumber(dto.documentNumber()).isPresent()){
            throw new RuntimeException("User with Document: "+dto.documentNumber()+" already exists");
        }

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("User with email: "+dto.email()+" already exists");
        }

        User user = User.builder()
                .name(dto.name())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .birthDate(dto.birthDate())
                .phoneNumber(dto.phoneNumber())
                .documentNumber(dto.documentNumber())
                .build();

        userRepository.save(user);
        int userAge = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
        return new UserDetailDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                userAge,
                user.getPhoneNumber(),
                user.getDocumentNumber()
        );
    }

    @Override
    public UserDetailDTO updateUser(UpdateUserDTO dto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDetailDTO findUserById(Long userId) {
        return null;
    }

    @Override
    public List<UserDetailDTO> findUserByName(String name) {
        return List.of();
    }

    @Override
    public AthleteDetailDTO createAthlete(CreateAthleteDTO dto) {
        return null;
    }

    @Override
    public AthleteDetailDTO getFullDetail(Long athleteId) {
        return null;
    }
}
