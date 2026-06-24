package io.holeshot.core.domain.port.in;


import io.holeshot.core.infrastructure.web.dtos.requests.CreateAthleteDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.CreateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.UpdateUserDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.AthleteDetailDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.UserDetailDTO;

import java.util.List;

public interface IUserService {
    UserDetailDTO createUser(CreateUserDTO dto);
    UserDetailDTO updateUser(UpdateUserDTO dto);
    void deleteUser(Long userId);
    UserDetailDTO findUserById(Long userId);
    List<UserDetailDTO> findUserByName(String name);

    //Athletes Only
    AthleteDetailDTO createAthlete(CreateAthleteDTO dto);
    AthleteDetailDTO getFullDetail(Long athleteId);
    //Update Athlete

    //Measurements
    // -> Registrar, Obtener listado completo, Obtener la medición mas reciente.

    //Injuries
    // -> Registrar Lesión, Cerrar Lesión, Obtener lesiones actuales de un deportista, Obtener historial completo de lesiones.

    //Bicycle
    // -> Registrar Bici, Obtener bicicletas de un atleta, Actualizar.

}
