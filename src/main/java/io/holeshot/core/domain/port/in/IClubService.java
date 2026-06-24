package io.holeshot.core.domain.port.in;

import io.holeshot.core.infrastructure.web.dtos.requests.CreateClubDTO;
import io.holeshot.core.infrastructure.web.dtos.requests.UpdateClubDTO;
import io.holeshot.core.infrastructure.web.dtos.responses.ClubDetailDTO;

public interface IClubService {
    ClubDetailDTO createClub(CreateClubDTO dto);
    ClubDetailDTO findByClubId(Long clubId);
    ClubDetailDTO findByName(String clubName);
    void deleteClub(Long clubId);
    ClubDetailDTO updateClub(UpdateClubDTO dto);

    //Horarios / Sessions
    // -> Registrar horario, Obtener horarios, Eliminar Horarios, Modificar horario.

    // Miembros
    // -> Obtener miembro por Id o Nombre, Obtener todos los miembros, Obtener miembros de preselección, etc.
}
