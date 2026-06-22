package io.holeshot.core.domain.port.in;

import io.holeshot.core.domain.model.user.User;

public interface IUserService {
    void registerAthlete();
    void registerCoach();
    void getProfileById();
    void getAll();
    void updateAthlete();
    void updatePhysicalMeasurements();
    void registerInjury();
    void getActiveInjuriesByAthlete();
}
