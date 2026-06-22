package io.holeshot.core.domain.port.in;

public interface IClubService {
    void createClub();
    void updateClubConfiguration();
    void getAthletes();
    void enrollAthleteToClub();
    void registerPaymentFromAthlete();
    void getPaymentFromAthlete();
}
